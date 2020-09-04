package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

public interface ISizeService {

    SizeEntity findSizeBySizeType(String sizeType);

    SizeEntity createNewOrUpdateSize(SizeEntity sizeEntity);

    SizeEntity getSizeToUpdate(Long id);

    SizeEntity addNewAvailableProduct(Long sizeId, AvailableProductEntity availableProductsEntity);


}
