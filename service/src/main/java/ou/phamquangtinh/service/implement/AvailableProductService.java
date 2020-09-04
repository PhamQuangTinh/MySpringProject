package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.repository.AvailableProductJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;

@Service
public class AvailableProductService implements IAvailableProductService {

    @Autowired
    private AvailableProductJPARepository availableProductJPARepository;

    @Override
    public AvailableProductEntity createNewAvailableProduct(AvailableProductEntity availableProductEntity) {
        return availableProductJPARepository.saveAndFlush(availableProductEntity);
    }
}
