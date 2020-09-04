package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.repository.SizeJPARepository;
import ou.phamquangtinh.service.component_service.ISizeService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class SizeService implements ISizeService {

    @Autowired
    private SizeJPARepository sizeJPARepository;

    @Override
    public SizeEntity findSizeBySizeType(String sizeType) {
        Optional<SizeEntity> sizeEntity = sizeJPARepository.findBySizeType(sizeType);
        return sizeEntity.orElse(null);
    }

    @Override
    public SizeEntity createNewOrUpdateSize(SizeEntity sizeEntity) {
        return sizeJPARepository.saveAndFlush(sizeEntity);
    }

    @Override
    public SizeEntity getSizeToUpdate(Long id) {
        return sizeJPARepository.getOne(id);
    }

    @Override
    public SizeEntity addNewAvailableProduct(Long sizeId, AvailableProductEntity availableProductsEntity) {
        SizeEntity sizeEntity = sizeJPARepository.getOne(sizeId);

        if(sizeEntity.getAvailableProductsEntities() == null){
            Collection<AvailableProductEntity> availableProductsEntities = new HashSet<>();
            availableProductsEntities.add(availableProductsEntity);
            sizeEntity.setAvailableProductsEntities(availableProductsEntities);
        }else{
            sizeEntity.getAvailableProductsEntities().add(availableProductsEntity);
        }

        return sizeJPARepository.saveAndFlush(sizeEntity);

    }
}
