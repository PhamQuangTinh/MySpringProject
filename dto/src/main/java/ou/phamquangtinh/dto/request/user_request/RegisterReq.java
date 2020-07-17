package ou.phamquangtinh.dto.request.user_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class RegisterReq {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private Collection<String> roles;
}
