package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.repository.AvailableProductJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IColorService;

import java.util.Optional;

@Service
public class AvailableProductService implements IAvailableProductService {

    @Autowired
    private AvailableProductJPARepository availableProductJPARepository;

    @Autowired
    private IColorService colorService;


    @Override
    public AvailableProductEntity createNewAvailableProduct(AvailableProductEntity availableProductEntity) {
        return availableProductJPARepository.saveAndFlush(availableProductEntity);
    }

    @Override
    public int checkUnitInOrderWithProductAndColorAndSize(Long proId, String colorLink, Long sizeId, int unitInOrder) {
        String linkColor = colorLink.substring(colorLink.lastIndexOf("\\") + 1);
        Long colorId = colorService.findColorByColorLinkContaining(linkColor).getId();
        AvailableProductEntity availableProductEntity = availableProductJPARepository
                .findById_ProductIdAndId_ColorIdAndId_SizeId(proId, colorId, sizeId);

        return (availableProductEntity.getUnitInStock() > unitInOrder) ? unitInOrder : availableProductEntity.getUnitInStock();

    }
}
