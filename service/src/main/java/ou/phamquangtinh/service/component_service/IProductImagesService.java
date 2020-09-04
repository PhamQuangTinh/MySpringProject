package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

import java.util.Set;

public interface IProductImagesService {

    ProductImagesEntity findProductImagesByImageLink(String imageLink);

    ProductImagesEntity createNewOrUpdateProductImages(ProductImagesEntity productImagesEntity);

    Set<ProductImagesEntity> findProductImagesByProductId(Long proId);

    Set<ProductImagesEntity> findProductImagesByProductIdAndColorId(Long proId, Long colorId);


}
