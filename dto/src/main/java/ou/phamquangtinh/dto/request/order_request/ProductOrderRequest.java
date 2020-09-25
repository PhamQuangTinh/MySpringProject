package ou.phamquangtinh.dto.request.order_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequest {
    private Long proId;
    private Long colorId;
    private Long sizeId;
    private int qty;
    private double finalPrice;
}
