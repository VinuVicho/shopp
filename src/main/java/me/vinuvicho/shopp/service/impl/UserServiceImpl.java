package me.vinuvicho.shopp.service.impl;

import lombok.AllArgsConstructor;
import me.vinuvicho.shopp.entity.user.User;
import me.vinuvicho.shopp.registration.token.ConfirmationToken;
import me.vinuvicho.shopp.registration.token.ConfirmationTokenService;
import me.vinuvicho.shopp.registration.token.TokenType;
import me.vinuvicho.shopp.repository.UserRepo;
import me.vinuvicho.shopp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService tokenService;
    
    @Override
    public User getUser(String credentials) {
        try {
            Long id = Long.valueOf(credentials);
            @SuppressWarnings("UnnecessaryLocalVariable")                //не вибиває помилку якщо відразу ретирн
            User user = userRepo.getById(id);
            return user;
        } catch (Exception e) {
            Optional<User> OUser =  userRepo.findByUsername(credentials);
            if (OUser.isPresent()) return OUser.get();
            throw new IllegalStateException("No user found");
        }
    }

    @Transactional
    public ConfirmationToken signUpUser(User user) {       //returns token to confirm
        boolean userExists =                                //TODO (optimize): make this using 1 db transaction
                userRepo.findByUsername(user.getUsername()).isPresent() ||
                        userRepo.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            User realUser =
                    userRepo.findByEmail(user.getEmail()).orElse(
                            userRepo.findByUsername(user.getUsername()).orElseThrow());
            if (realUser.isEnabled())
                throw new IllegalStateException("User with such email or username already exists");
            else {
                ConfirmationToken token = tokenService.getVerifyTokenByUser(realUser);
                if (token.getExpiresAt().minusMinutes(5).isAfter(LocalDateTime.now())) {
                    return token;
                }
                Long userId = token.getUser().getId();
                tokenService.deleteToken(token.getToken());
                userRepo.deleteById(userId);
            }
        }
        //Steal password here   xd
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);

        ConfirmationToken token = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user,
                TokenType.VERIFY_ACCOUNT
        );
        tokenService.saveConfirmationToken(token);
        return token;
    }

    public void enableUser(String email) {
        userRepo.enableUser(email);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
