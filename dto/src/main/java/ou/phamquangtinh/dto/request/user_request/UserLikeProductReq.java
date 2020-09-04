package ou.phamquangtinh.dto.request.user_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLikeProductReq {
    private Long proId;
    private Long userId;
}
