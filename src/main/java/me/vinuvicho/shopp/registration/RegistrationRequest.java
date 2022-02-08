package me.vinuvicho.shopp.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@ToString
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;

    public RegistrationRequest() {
    }
}
