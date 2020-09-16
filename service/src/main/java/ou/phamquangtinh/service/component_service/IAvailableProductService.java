package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

public interface IAvailableProductService {

    AvailableProductEntity createNewAvailableProduct(AvailableProductEntity availableProductEntity);

    int checkUnitInOrderWithProductAndColorAndSize(Long proId, String colorLink, Long sizeId, int unitInOrder);
}
