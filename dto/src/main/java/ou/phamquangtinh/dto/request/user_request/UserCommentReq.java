package ou.phamquangtinh.dto.request.user_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCommentReq{
    private Long userId;
    private Long productId;
    private String content;
}
