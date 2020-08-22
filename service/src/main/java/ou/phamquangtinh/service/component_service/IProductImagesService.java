package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SizeEntity;

public interface IProductImagesService {

    ProductImagesEntity findProductImagesByImageLink(String imageLink);

    ProductImagesEntity createNewOrUpdateProductImages(ProductImagesEntity productImagesEntity);

}
