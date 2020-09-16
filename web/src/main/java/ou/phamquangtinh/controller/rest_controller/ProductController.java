package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.service.component_service.IProductService;

import java.util.Set;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @GetMapping("/get/all_product")
    public ResponseEntity<Object> getAllProductsPagination(@RequestParam("page") int page,
                                                           @RequestParam("size") int size,
                                                           @RequestParam("sort") String sort) {

        return ResponseEntity.ok(productService.getAllProductPagination(page, size, sort));

    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Object> getProductByProductId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/get/cate_id/{id}")
    public ResponseEntity<Object> getProductByCategoryId(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductByCategoryId(id, page, size));
    }

    @GetMapping("/get/cate_name/{name}")
    public ResponseEntity<Object> getProductByCategoryName(@PathVariable("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductByCategoryName(name, page, size));
    }

    @GetMapping("/get/sub_cate_id/{id}")
    public ResponseEntity<Object> getProductBySubCategoryId(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductBySubCategoryId(id, page, size));
    }

    @GetMapping("/get/sub_cate_name/{name}")
    public ResponseEntity<Object> getProductBySubCategoryName(@PathVariable("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductBySubCategoryName(name, page, size));
    }

    @GetMapping("/get/color/{id}")
    public ResponseEntity<Object> getProductByColorId(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductByColorId(id, page, size));
    }

    @GetMapping("/get/size/{id}")
    public ResponseEntity<Object> getProductBySizeId(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(productService.findProductBySizeId(id, page, size));
    }

    //Tìm kiếm sản phẩm theo tên hoặc mô tả
    @GetMapping("/get/product_name_or_description")
    public ResponseEntity<Object> searchProductByProductNameOrDescription(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(productService.findProductProductNameOrDescription(keyword));
    }

    @GetMapping("/get/top_10_like_product")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<Object> getTop10Product(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(productService.findTop10LikeProduct(userId));
    }

    @GetMapping("/get/product_info")
    public ResponseEntity<Object> getProductInfo(@RequestParam("pro_id") Long proId,
                                                 @RequestParam("user_id") Long userId) {
        return ResponseEntity.ok(productService.getProductInfo(proId, userId));
    }

    @GetMapping("/get/sex_type_and_price")
    public ResponseEntity<Object> getProductBySexTypeAndPrice(@RequestParam("page") int page,
                                                              @RequestParam("size") int size,
                                                              @RequestParam("sort") String sort,
                                                              @RequestParam("fPrice") double fPrice,
                                                              @RequestParam("lPrice") double lPrice,
                                                              @RequestParam("colorName") String colorName,
                                                              @RequestParam("sexTypes") Set<String> sexTypes) {
        return ResponseEntity.ok(productService.findProductBySexTypeAndUnitPrice(sexTypes, fPrice, lPrice, colorName, page, size, sort));
    }

    @GetMapping("/get/product_like")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public ResponseEntity<Object> getProductByLikeList(@RequestParam("page") int page,
                                                       @RequestParam("size") int size,
                                                       @RequestParam("sort") String sort,
                                                       @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(productService.findListLikeProduct( page, size, sort, userId));
    }

    @GetMapping("/get/product_name")
    public ResponseEntity<Object> getProductByProductName(@RequestParam("page") int page,
                                                       @RequestParam("size") int size,
                                                       @RequestParam("sort") String sort,
                                                       @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(productService.findProductByProductNamePagination( page, size, sort, keyword));
    }

}
