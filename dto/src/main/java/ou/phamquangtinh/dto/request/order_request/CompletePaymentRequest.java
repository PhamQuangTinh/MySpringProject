package ou.phamquangtinh.dto.request.order_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletePaymentRequest {
    private String payerId;
    private String paymentId;
    private Long orderId;
}
