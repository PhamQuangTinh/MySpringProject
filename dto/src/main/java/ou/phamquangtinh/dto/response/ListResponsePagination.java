package ou.phamquangtinh.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListResponsePagination<T> {
    private List<T> listResponse;

    private PageMetadata pageMetadata;

}
