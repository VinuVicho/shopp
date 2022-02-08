package me.vinuvicho.shopp.service;

import me.vinuvicho.shopp.entity.user.User;
import me.vinuvicho.shopp.registration.token.ConfirmationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    void deleteUser(Long id);

    void enableUser(String email);

    User getUser(String credentials);

    ConfirmationToken signUpUser(User user);
}
