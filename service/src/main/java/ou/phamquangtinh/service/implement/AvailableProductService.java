package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.response.available_product_response.CheckCartItemResponse;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductKey;
import ou.phamquangtinh.repository.AvailableProductJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IColorService;

@Service
public class AvailableProductService implements IAvailableProductService {

    @Autowired
    private AvailableProductJPARepository availableProductJPARepository;

    @Autowired
    private IColorService colorService;


    @Override
    public AvailableProductEntity createNewOrUpdateAvailableProduct(AvailableProductEntity availableProductEntity) {
        return availableProductJPARepository.saveAndFlush(availableProductEntity);
    }

    @Override
    public CheckCartItemResponse checkUnitInOrderWithProductAndColorAndSize(Long proId, String colorLink, Long sizeId, int unitInOrder) {
        String linkColor = colorLink.substring(colorLink.lastIndexOf("\\") + 1);
        Long colorId = colorService.findColorByColorLinkContaining(linkColor).getId();
        AvailableProductEntity availableProductEntity = findAvailableProductByProIdAndColorIdAndSizeId(proId, colorId, sizeId);

        return (availableProductEntity.getUnitInStock() > unitInOrder) ? new CheckCartItemResponse(unitInOrder, availableProductEntity.getId().getColorId())
                : new CheckCartItemResponse(availableProductEntity.getUnitInStock(), availableProductEntity.getId().getColorId());

    }

    @Override
    public AvailableProductEntity findAvailableProductByProIdAndColorIdAndSizeId(Long proId, Long colorId, Long sizeId) {
        return availableProductJPARepository
                .findById_ProductIdAndId_ColorIdAndId_SizeId(proId, colorId, sizeId).orElse(null);

    }

    @Override
    public AvailableProductEntity getAvailableProductToUpdate(Long proId, Long colorId, Long sizeId) {
        AvailableProductKey availableProductKey = new AvailableProductKey(proId, colorId, sizeId);
        return availableProductJPARepository.getOne(availableProductKey);
    }

    @Override
    public AvailableProductEntity findAvailableProductById(Long proId, Long colorId, Long sizeId) {
        AvailableProductKey availableProductKey = new AvailableProductKey(proId, colorId, sizeId);
        return availableProductJPARepository.findById(availableProductKey).orElse(null);
    }

}
