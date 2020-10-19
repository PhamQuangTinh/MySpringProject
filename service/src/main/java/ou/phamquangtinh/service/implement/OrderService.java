package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.dto.request.order_request.PaymentRequest;
import ou.phamquangtinh.dto.response.FindOrderResponse;
import ou.phamquangtinh.dto.response.GetOrderByUserIdResponse;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.PageMetadata;
import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.OderDetailKey;
import ou.phamquangtinh.repository.OrderJPARepository;
import ou.phamquangtinh.service.component_service.*;
import ou.phamquangtinh.service.util.CommonUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CommonUtil commonUtil;

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
    public List<GetOrderByUserIdResponse> getOrderById(Long userId) {
        List<OrderEntity> orderEntity = orderJPARepository.findByUserEntity_Id(userId);

        if(orderEntity != null){
            return orderEntity.stream().map(x->{
                GetOrderByUserIdResponse response = new GetOrderByUserIdResponse();
                response.setId(x.getId());
                response.setCreateDated(x.getCreatedDate());
                response.setPayerId(x.getPayerId());
                response.setPaymentId(x.getPaymentId());
                response.setTotalItems(x.getOrderDetail().size());
                response.setTotalPrice(x.getOrderDetail().stream()
                        .mapToDouble(y-> y.getQuantity() * y.getProductEntity().getUnitPrice()).sum());
                return response;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void deleteOrderAdmin(Long id) {
        orderJPARepository.deleteById(id);
    }

    @Override
    public ListResponsePagination getAllOrderService(int page, int size, String sortBy) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1,size,sort);
        Page<OrderEntity> userEntityList = orderJPARepository.findAll(pageable);
        ListResponsePagination res = new ListResponsePagination();
        List<FindOrderResponse> listRes = userEntityList.getContent().stream().map(x->{
            FindOrderResponse order = new FindOrderResponse();
            order.setId(x.getId());
            order.setCreatedBy(x.getCreatedBy());
            order.setCreatedDate(x.getCreatedDate());
            order.setPayerId(x.getPayerId());
            order.setPaymentId(x.getPaymentId());
            order.setUserId(x.getUserEntity().getId());
            return order;
        }).collect(Collectors.toList());
        PageMetadata pageMetadata = new PageMetadata();
        pageMetadata.setPage(userEntityList.getNumber() + 1);
        pageMetadata.setSize(userEntityList.getSize());
        pageMetadata.setTotalElements(userEntityList.getTotalElements());
        pageMetadata.setTotalPages(userEntityList.getTotalPages());
        pageMetadata.setNumberOfElements(userEntityList.getNumberOfElements());
        res.setListResponse(listRes);
        res.setPageMetadata(pageMetadata);
        return res;
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
