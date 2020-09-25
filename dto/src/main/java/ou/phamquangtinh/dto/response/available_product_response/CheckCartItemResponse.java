package ou.phamquangtinh.dto.response.available_product_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckCartItemResponse {
    private int qty;
    private Long colorId;
}
