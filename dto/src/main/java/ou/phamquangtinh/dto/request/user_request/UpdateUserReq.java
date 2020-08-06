package ou.phamquangtinh.dto.request.user_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserReq {
    private Long id;
    private String username;
    private String oldPass;
    private String newPass;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
}
