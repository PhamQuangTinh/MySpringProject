package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

public interface IColorService {

    ColorEntity findColorByColorLink(String colorLink);

    ColorEntity createNewOrUpdateColor(ColorEntity colorEntity);

    ColorEntity getColorToUpdate(Long id);

    ColorEntity addNewProductColor(Long colorId, ProductColorEntity productColorEntity);

    ListResponsePagination findColorByProductId(Long proId, int page, int size);

}
