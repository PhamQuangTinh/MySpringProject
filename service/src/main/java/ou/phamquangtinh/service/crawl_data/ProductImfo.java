package ou.phamquangtinh.service.crawl_data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductImfo {

    private String productName;

    private String description;

    private String sexType;

    private double unitPrice;

    private List<String> images = new ArrayList<>();
}
