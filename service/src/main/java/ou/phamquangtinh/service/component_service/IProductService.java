package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.ProductInfoResponse;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductAvatarEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;

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
                                                            int page, int size, String sortBy);

    List<ProductEntity> findProductProductNameOrDescription(String keyword);

    List<ProductEntity> findTop10LikeProduct(Long userId);

    ProductInfoResponse getProductInfo(Long proId);

    void addNewCommentToProduct(ProductEntity productEntity, ProductCommentEntity productCommentEntity);

}
