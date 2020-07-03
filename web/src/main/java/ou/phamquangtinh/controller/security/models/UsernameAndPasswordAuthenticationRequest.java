package ou.phamquangtinh.controller.security.models;

import lombok.*;


@Getter
@Setter
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
