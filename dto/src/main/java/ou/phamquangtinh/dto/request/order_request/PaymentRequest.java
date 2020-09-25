package ou.phamquangtinh.dto.request.order_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String currency;
    private String description;
    private List<ProductOrderRequest> proInfo;
}
