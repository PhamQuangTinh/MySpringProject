package ou.phamquangtinh.controller.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.service.component_service.IProductImagesService;

import java.util.Set;

@RestController
@RequestMapping("/api/product_images")
public class ProductImagesController {

    @Autowired
    private IProductImagesService productImagesService;

    @GetMapping("/get/product_id")
    private ResponseEntity<Set<ProductImagesEntity>> getProductImagesByProductId(@RequestParam("productId") Long id){
        return  ResponseEntity.ok(productImagesService.findProductImagesByProductId(id));
    }

        @GetMapping("/get/color_id_and_pro_id")
    private ResponseEntity<Set<ProductImagesEntity>> getProductImagesByProductIdAndColorId(@RequestParam("productId") Long proid,
                                                                                           @RequestParam("colorId") Long colorId){
        return  ResponseEntity.ok(productImagesService.findProductImagesByProductIdAndColorId(proid, colorId));
    }
}
