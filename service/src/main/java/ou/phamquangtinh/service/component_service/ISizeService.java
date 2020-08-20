package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.SizeEntity;

public interface ISizeService {

    SizeEntity findSizeBySizeType(String sizeType);

    SizeEntity createNewSize(SizeEntity sizeEntity);
}
