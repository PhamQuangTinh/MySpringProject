package ou.phamquangtinh.dto.request.order_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {
    private double unitPrice;
    private String currency;
    private String description;
}
