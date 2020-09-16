package ou.phamquangtinh.controller.rest_controller;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.dto.request.order_request.CompletePaymentRequest;
import ou.phamquangtinh.dto.request.order_request.PaymentRequest;
import ou.phamquangtinh.dto.response.StringResponse;
import ou.phamquangtinh.service.component_service.IOrderService;
import ou.phamquangtinh.service.component_service.IPaypalService;

import java.util.List;

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
    private static final String PAYMENT_INTENT = "pay";

        @PostMapping("/payment/make/payment")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<?> payment(@RequestBody PaymentRequest paymentRequest){
        try{
            Payment payment = paypalService.createPayment(paymentRequest.getUnitPrice(), paymentRequest.getCurrency(),
                    PAYMENT_METHOD, PAYMENT_INTENT, paymentRequest.getDescription(), CANCEL_URL, SUCCESS_URL);

            for(Links link: payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    return ResponseEntity.ok(new StringResponse(link.getHref()));
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new StringResponse("http://localhost:4200/check-out"));
    }


    @PostMapping("/payment/completion")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<?> successPay(@RequestBody CompletePaymentRequest request) {
        try {
            System.out.println("Success");
            Payment payment = paypalService.executePayment(request.getPaymentId(), request.getPayerId());
//            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return ResponseEntity.ok(new StringResponse("/home"));
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(new StringResponse("/filter"));
    }


}
