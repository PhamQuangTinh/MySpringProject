package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.repository.SizeJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.ISizeService;

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
    public SizeEntity createNewSize(SizeEntity sizeEntity) {
        return sizeJPARepository.saveAndFlush(sizeEntity);
    }
}
