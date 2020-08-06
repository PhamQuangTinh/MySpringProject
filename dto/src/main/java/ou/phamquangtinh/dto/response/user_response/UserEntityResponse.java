package ou.phamquangtinh.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityResponse {
    private String id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private List<String> rolesResponse;
}
