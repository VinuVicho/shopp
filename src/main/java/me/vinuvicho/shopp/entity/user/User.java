package me.vinuvicho.shopp.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;


@SuppressWarnings("JpaDataSourceORMInspection")         //prob to delete (just remove warning)
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")                              //user -- ключове слово в Postgre
public class User implements UserDetails {
    //TODO: notifications

    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String username;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String profilePhoto = null;
    private String about = null;
    private LocalDateTime createdAt = null;
    private LocalDateTime lastActivity = null;
    private boolean collapseMessages = false;

    @ToString.Exclude
    private boolean locked = false;
    @ToString.Exclude
    private boolean enabled = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getGrantedAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
