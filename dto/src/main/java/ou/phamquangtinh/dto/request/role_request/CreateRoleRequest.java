package ou.phamquangtinh.dto.request.role_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateRoleRequest {

    private String code;

    private String name;
}
