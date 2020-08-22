package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;

public interface IColorService {

    ColorEntity findColorByColorLink(String colorLink);

    ColorEntity createNewOrUpdateColor(ColorEntity colorEntity);

    ColorEntity getColorToUpdate(Long id);

    ColorEntity addNewProductImage(Long colorId, ProductImagesEntity productImagesEntity);

    ColorEntity addNewAvailableProduct(Long colorId, AvailableProductsEntity availableProductsEntity);

}
