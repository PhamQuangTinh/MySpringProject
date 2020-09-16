package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.request.order_request.CheckCartItems;

import java.util.List;

public interface IOrderService {
    Long checkCartItems(List<CheckCartItems> cartItems);

}
