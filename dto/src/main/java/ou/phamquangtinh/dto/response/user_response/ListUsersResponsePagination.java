package ou.phamquangtinh.dto.response.user_response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ou.phamquangtinh.dto.response.PageMetadata;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListUsersResponsePagination {
    private List<UserEntityResponse> users;

    private PageMetadata pageMetadata;

}
