package ou.phamquangtinh.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FindOrderResponse {
    private Long id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String payerId;
    private String paymentId;
    private Long userId;
}
