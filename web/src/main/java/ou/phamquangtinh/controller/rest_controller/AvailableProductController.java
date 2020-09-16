package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.dto.request.order_request.CheckCartItems;
import ou.phamquangtinh.service.component_service.IAvailableProductService;

@RestController
@RequestMapping("/api/available_product")
public class AvailableProductController {

    @Autowired
    private IAvailableProductService availableProductService;


        @PostMapping("/post/cart_checking")
    private ResponseEntity<Object> checkCardToOrder(@RequestBody CheckCartItems checkCartItems){
        return ResponseEntity.ok(availableProductService.checkUnitInOrderWithProductAndColorAndSize(checkCartItems.getProId(),
                checkCartItems.getColorLink(), checkCartItems.getSizeId(), checkCartItems.getUnitInOrder()));
    }

}
