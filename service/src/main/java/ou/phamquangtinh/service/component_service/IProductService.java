package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.request.CreateNewProductResquest;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.ProductInfoResponse;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductAvatarEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;

import java.util.List;
import java.util.Set;

public interface IProductService {

    ProductEntity findProductByProductName(String productName);

    ProductEntity createNewOrUpdateProduct(ProductEntity productEntity);

    ProductEntity findProductById(Long id);

    ProductEntity getProductToUpdate(Long id);


    ProductEntity addNewCategory(Long productId, CategoryEntity categoryEntity);

    ProductEntity addNewProductAvatar(Long productId, ProductAvatarEntity productAvatarEntity);

    ProductEntity addNewProductColor(Long productId, ProductColorEntity productColorEntity);

    ListResponsePagination getAllProductPagination(int page, int size, String sortBy);

    ProductEntity addNewSubCategory(Long productId, SubCategoryEntity subCategoryEntity);

    ListResponsePagination findProductByCategoryId(Long cateId, int page, int size);

    ListResponsePagination findProductByCategoryName(String categoryName, int page, int size);

    ListResponsePagination findProductBySubCategoryId(Long subCateId, int page, int size);

    ListResponsePagination findProductBySubCategoryName(String subCategoryName, int page, int size);

    ListResponsePagination findProductByColorId(Long colorId, int page, int size);

    ListResponsePagination findProductBySizeId(Long sizeId, int page, int size);

    ListResponsePagination findProductBySexTypeAndUnitPrice(Set<String> sexTypes, double fPrice, double lPrice,
                                                            String colorName, int page, int size, String sortBy);

    ListResponsePagination findListLikeProduct(int page, int size, String sortBy, Long userId);

    ListResponsePagination findProductByProductNamePagination(int page, int size, String sortBy, String keyword);

    List<ProductEntity> findProductProductNameOrDescription(String keyword);

    List<ProductEntity> findTop10LikeProduct(Long userId);

    ProductInfoResponse getProductInfo(Long proId, Long userId);


    void addNewOrderDetail(Long proId, OrderDetailEntity orderDetailEntity);

    void addNewProductCommentDetail(Long proId, ProductCommentDetailEntity productCommentDetailEntity);

    ListResponsePagination findProductBySexType(String sType, int page, int size, String sortBy);

    ListResponsePagination findProductBySexTypeAndCategory(String sType, String categoryName, int page, int size, String sortBy);

    List<ProductEntity> findTop10SuperCategoryProduct(String categoryName, String sType);

    List<ProductEntity> findTop12ProductsByCategory(Set<String> cates);

    ProductEntity createProductAdmin(CreateNewProductResquest request);

    ProductEntity updateProductAdmin(CreateNewProductResquest request);

    void deleteProductAdmin(Long proid);
}
