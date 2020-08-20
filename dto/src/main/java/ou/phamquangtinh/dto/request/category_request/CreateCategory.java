package ou.phamquangtinh.dto.request.category_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCategory {
    private String categoryName;
    private String description;
}
