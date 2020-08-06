package ou.phamquangtinh.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMetadata {
    private int page;

    private int size;

    private Long totalElements;

    private int totalPages;
}
