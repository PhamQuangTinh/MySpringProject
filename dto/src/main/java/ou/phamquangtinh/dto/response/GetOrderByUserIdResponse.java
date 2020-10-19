package ou.phamquangtinh.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class GetOrderByUserIdResponse {
    private Long id;
    private String payerId;
    private String paymentId;
    private int totalItems;
    private double totalPrice;
    private LocalDateTime createDated;
}
