package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.dto.request.order_request.PaymentRequest;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.OderDetailKey;
import ou.phamquangtinh.repository.OrderJPARepository;
import ou.phamquangtinh.service.component_service.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private OrderJPARepository orderJPARepository;

    @Autowired
    private IAvailableProductService availableProductService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IOrderService orderService;


    @Override
    public OrderEntity createNewOrUpdateOrderEntity(OrderEntity orderEntity) {
        return orderJPARepository.saveAndFlush(orderEntity);
    }

    @Override
    public void addNewOrderDetail(Long orderId, OrderDetailEntity orderDetailEntity) {
        Optional<OrderEntity> orderEntity = orderJPARepository.findById(orderId);

        orderEntity.ifPresent(x -> {
            if (x.getOrderDetail() == null) {
                Collection<OrderDetailEntity> orderDetailEntities = new HashSet<>();
                x.setOrderDetail(orderDetailEntities);
            } else {
                x.getOrderDetail().add(orderDetailEntity);
            }
            createNewOrUpdateOrderEntity(x);
        });
    }

    @Override
    public OrderEntity getOrderToUpdate(Long orderId) {
        return orderJPARepository.getOne(orderId);
    }

    @Override
    public void removeOrder(Long orderId) {
        OrderEntity orderEntity = getOrderToUpdate(orderId);
        orderJPARepository.delete(orderEntity);
    }

    @Override
    @Transactional
    public Long orderTransaction(PaymentRequest paymentRequest) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity userEntity = userService.findByUsername(userName);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserEntity(userEntity);
        final OrderEntity orderRes = orderService.createNewOrUpdateOrderEntity(orderEntity);
        userService.addNewOrder(userEntity.getId(), orderRes);
        paymentRequest.getProInfo().forEach(x -> {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            OderDetailKey orderDetailKey = new OderDetailKey(orderRes.getId(), x.getProId());
            orderDetailEntity.setId(orderDetailKey);
            orderDetailEntity.setOrderEntity(orderRes);
            orderDetailEntity.setProductEntity(productService.findProductById(x.getProId()));
            orderDetailEntity.setQuantity(x.getQty());
            orderDetailEntity.setColorId(x.getColorId());
            orderDetailEntity.setSizeId(x.getSizeId());

            orderDetailEntity = orderDetailService.createNewOrUpdateOrderDetail(orderDetailEntity);

            productService.addNewOrderDetail(x.getProId(), orderDetailEntity);

            orderService.addNewOrderDetail(orderRes.getId(), orderDetailEntity);


            AvailableProductEntity availableProductUpdate = availableProductService
                    .getAvailableProductToUpdate(x.getProId(), x.getColorId(), x.getSizeId());
            if (availableProductUpdate != null) {

                availableProductUpdate.setUnitInStock(availableProductUpdate.getUnitInStock() - x.getQty());
                availableProductUpdate.setUnitInOrder(availableProductUpdate.getUnitInOrder() + x.getQty());
                availableProductService.createNewOrUpdateAvailableProduct(availableProductUpdate);
            }

        });

        if (orderDetailService.findOrderDetailsByOrderId(orderRes.getId()) == null) {
            orderJPARepository.delete(orderRes);
            return null;
        }
        return orderRes.getId();
    }
}
