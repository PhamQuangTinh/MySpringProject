package ou.phamquangtinh.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityResponse {
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private LocalDateTime createdDate;
    private List<String> rolesResponse;
    private String roleString;
}
