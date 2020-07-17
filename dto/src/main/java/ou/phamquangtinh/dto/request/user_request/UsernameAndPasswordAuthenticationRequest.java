package ou.phamquangtinh.dto.request.user_request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
