package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.repository.AvailableProductJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;

@Service
public class AvailableProductService implements IAvailableProductService {

    @Autowired
    private AvailableProductJPARepository availableProductJPARepository;

    @Override
    public AvailableProductsEntity createNewAvailableProduct(AvailableProductsEntity availableProductsEntity) {
        return availableProductJPARepository.saveAndFlush(availableProductsEntity);
    }
}
