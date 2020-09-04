package ou.phamquangtinh.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ou.phamquangtinh.dto.response.model.ColorModel;
import ou.phamquangtinh.dto.response.model.ProductImagesModel;
import ou.phamquangtinh.dto.response.model.SizeModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoResponse {
    private String productName;
    private String description;
    private double unitPrice;
    private double discount;

    private List<ColorModel> colors;
    private List<SizeModel> size;
    private List<ProductImagesModel> firstImagesColor;
}


