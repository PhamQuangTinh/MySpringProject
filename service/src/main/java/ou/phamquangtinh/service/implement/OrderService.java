package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IOrderService;

import java.util.List;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private IAvailableProductService availableProductService;

    @Override
    public Long checkCartItems(List<CheckCartItems> cartItems) {
        for (CheckCartItems cartItem : cartItems) {

        }

        return 0L;

    }
}
