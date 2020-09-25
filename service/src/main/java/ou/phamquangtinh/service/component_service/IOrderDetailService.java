package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {

    OrderDetailEntity createNewOrUpdateOrderDetail(OrderDetailEntity orderDetailEntity);

    List<OrderDetailEntity> findOrderDetailsByOrderId(Long orderId);
}
