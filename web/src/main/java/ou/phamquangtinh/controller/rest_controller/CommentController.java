package ou.phamquangtinh.controller.rest_controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.dto.request.user_request.UserCommentReq;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.service.component_service.ICommentDetailService;
import ou.phamquangtinh.service.component_service.IProductService;
import ou.phamquangtinh.service.component_service.IUserCommentService;

import java.util.List;

@RestController
@RequestMapping("/api/product_comment")
public class CommentController {

    @Autowired
    private IUserCommentService commentService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICommentDetailService commentDetailService;

    @PostMapping("/post/comment-creation")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<?> createNewCommentForProduct(@RequestBody UserCommentReq req){
        return ResponseEntity.ok(commentService.createNewCommentToProduct(req.getUserId(), req.getProductId(), req.getContent()));
    }

    @GetMapping("/get/all-comment")
    public ResponseEntity<?> getAllCommentsProduct(@RequestParam("page") int page, @RequestParam("proId") Long proId){
        List<ProductCommentDetailEntity> productCommentDetailEntityList = commentDetailService.findAllCommentsProduct(page, proId);
        return ResponseEntity.ok(productCommentDetailEntityList);
    }
}
