package ou.phamquangtinh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CreateNewProductResquest {

    private Long id;

    private String productName;

    private double unitPrice;

    private String description;

    private double discount;

    private String sexType;
}
