package ou.phamquangtinh.controller.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.service.component_service.IProductColorService;

@RestController
@RequestMapping("/api/product_color")
public class ProductColorController {


    @Autowired
    private IProductColorService productColorService;

    @GetMapping("/get/proId_and_color_id")
    private ResponseEntity getProductColorByProIdAndColorId(@RequestParam("pro_id") Long proId,
                                                            @RequestParam("color_id") Long colorId){
        return ResponseEntity.ok(productColorService.finProductColorByProductIdAndColorId(proId,colorId));
    }
}
