package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.repository.ColorJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IColorService;

import java.util.Optional;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorJPARepository colorJPARepository;

    @Override
    public ColorEntity findColorByColorLink(String colorLink) {
        Optional<ColorEntity> colorEntity = colorJPARepository.findByColorLink(colorLink);
        return colorEntity.orElse(null);
    }

    @Override
    public ColorEntity createNewColor(ColorEntity colorEntity) {
        return colorJPARepository.saveAndFlush(colorEntity);
    }
}
