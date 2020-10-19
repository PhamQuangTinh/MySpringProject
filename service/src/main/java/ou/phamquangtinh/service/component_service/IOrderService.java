package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.dto.request.order_request.PaymentRequest;
import ou.phamquangtinh.dto.response.GetOrderByUserIdResponse;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import java.util.List;

public interface IOrderService {

    OrderEntity createNewOrUpdateOrderEntity(OrderEntity orderEntity);

    void addNewOrderDetail(Long orderId, OrderDetailEntity orderDetailEntity);

    OrderEntity getOrderToUpdate(Long orderId);

    void removeOrder(Long orderId);

    Long orderTransaction(PaymentRequest paymentRequest);

    ListResponsePagination getAllOrderService(int page, int size, String sort);

    void deleteOrderAdmin(Long id);

    List<GetOrderByUserIdResponse> getOrderById(Long userId);
}
