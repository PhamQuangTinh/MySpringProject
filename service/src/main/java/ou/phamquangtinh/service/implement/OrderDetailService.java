package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.repository.OrderDetailJPARepository;
import ou.phamquangtinh.service.component_service.IOrderDetailService;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private OrderDetailJPARepository orderDetailJPARepository;

    @Override
    public OrderDetailEntity createNewOrUpdateOrderDetail(OrderDetailEntity orderDetailEntity) {
        return orderDetailJPARepository.saveAndFlush(orderDetailEntity);
    }

    @Override
    public List<OrderDetailEntity> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailJPARepository.findByOrderEntity_Id(orderId);
    }
}
