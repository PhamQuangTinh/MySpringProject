package ou.phamquangtinh.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private String username;
    private String password;
}
