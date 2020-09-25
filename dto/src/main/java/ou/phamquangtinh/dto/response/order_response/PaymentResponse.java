package ou.phamquangtinh.dto.response.order_response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String url;
    private Long orderId;
}
