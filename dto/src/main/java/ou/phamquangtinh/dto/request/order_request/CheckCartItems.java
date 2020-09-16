package ou.phamquangtinh.dto.request.order_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckCartItems {
    private Long proId;
    private Long sizeId;
    private String colorLink;
    private int unitInOrder;
}
