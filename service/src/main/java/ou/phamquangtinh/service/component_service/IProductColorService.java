package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.ProductColorKey;
import ou.phamquangtinh.service.implement.ProductService;

import java.util.List;
import java.util.Set;

public interface IProductColorService {

    ProductColorEntity findProductColorById(ProductColorKey id);

    List<ProductColorEntity> findProductColorByProductId(Long id);

    ProductColorEntity createOrUpdateProductColor(ProductColorEntity productColorEntity);

    ProductColorEntity addNewAvailableProduct(ProductColorKey id, AvailableProductEntity availableProductEntity);

    ProductColorEntity addNewProductImages(ProductColorKey id, ProductImagesEntity productImagesEntity);

    List<String> finProductColorByProductIdAndColorId(Long proId, Long colorId);

}
