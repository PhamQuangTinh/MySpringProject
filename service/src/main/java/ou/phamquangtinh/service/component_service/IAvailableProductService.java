package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.available_product_response.CheckCartItemResponse;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;

public interface IAvailableProductService {

    AvailableProductEntity createNewOrUpdateAvailableProduct(AvailableProductEntity availableProductEntity);

    CheckCartItemResponse checkUnitInOrderWithProductAndColorAndSize(Long proId, String colorLink, Long sizeId, int unitInOrder);

    AvailableProductEntity findAvailableProductByProIdAndColorIdAndSizeId(Long proId, Long colorId, Long sizeId);

    AvailableProductEntity getAvailableProductToUpdate(Long proId, Long colorId, Long sizeId);

    AvailableProductEntity findAvailableProductById(Long proId, Long colorId, Long sizeId);

}
