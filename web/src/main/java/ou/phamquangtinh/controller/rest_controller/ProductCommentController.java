package ou.phamquangtinh.controller.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.service.component_service.IProductCommentService;

@RestController
@RequestMapping("/api/product_comment")
public class ProductCommentController {

    @Autowired
    private IProductCommentService productCommentService;

    @GetMapping("/get/product_comment/{proId}")
    private ResponseEntity<Object> getAllCommentsProduct(@RequestParam("page") int page, @PathVariable("proId") Long proId){
        return ResponseEntity.ok(productCommentService.findCommentsProduct(proId,page));
    }
}
