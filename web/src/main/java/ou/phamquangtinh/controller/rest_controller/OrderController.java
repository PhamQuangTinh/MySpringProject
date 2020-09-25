package ou.phamquangtinh.controller.rest_controller;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.controller.security.service.MyUserDetailService;
import ou.phamquangtinh.controller.security.service.MyUserDetails;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.dto.request.order_request.CompletePaymentRequest;
import ou.phamquangtinh.dto.request.order_request.PaymentRequest;
import ou.phamquangtinh.dto.request.order_request.ProductOrderRequest;
import ou.phamquangtinh.dto.response.StringResponse;
import ou.phamquangtinh.dto.response.order_response.PaymentResponse;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.OderDetailKey;
import ou.phamquangtinh.service.component_service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IPaypalService paypalService;


    private static final String SUCCESS_URL = "http://localhost:4200/check-out";
    private static final String CANCEL_URL = "http://localhost:4200/products/women";
    private static final String PAYMENT_METHOD = "paypal";
    private static final String PAYMENT_INTENT = "sale";

    @PostMapping("/payment/make/payment")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<?> payment(@RequestBody PaymentRequest paymentRequest) {
        try {

            double finalPrice = paymentRequest.getProInfo().stream().mapToDouble(x -> x.getFinalPrice() * x.getQty()).sum();

            Long orderId = orderService.orderTransaction(paymentRequest);
            if(orderId != null){
                Payment payment = paypalService.createPayment(finalPrice, paymentRequest.getCurrency(),
                        PAYMENT_METHOD, PAYMENT_INTENT, paymentRequest.getDescription(), CANCEL_URL, SUCCESS_URL);

                for (Links link : payment.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        return ResponseEntity.ok(new PaymentResponse(link.getHref(), orderId));
                    }
                }

            }


        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new PaymentResponse("http://localhost:4200/check-out", 0L));

    }


    @PostMapping("/payment/completion")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<?> successPay(@RequestBody CompletePaymentRequest request) {
        try {
            Payment payment = paypalService.executePayment(request.getPaymentId(), request.getPayerId());
//            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                OrderEntity orderEntity = orderService.getOrderToUpdate(request.getOrderId());
                orderEntity.setPayerId(request.getPayerId());
                orderEntity.setPaymentId(request.getPaymentId());
                orderService.createNewOrUpdateOrderEntity(orderEntity);
                return ResponseEntity.ok(new StringResponse("success"));
            }

        } catch (PayPalRESTException e) {
            orderService.removeOrder(request.getOrderId());
            System.out.println(e.getMessage());
        }
        orderService.removeOrder(request.getOrderId());
        return ResponseEntity.ok(new StringResponse("fail"));
    }


}
