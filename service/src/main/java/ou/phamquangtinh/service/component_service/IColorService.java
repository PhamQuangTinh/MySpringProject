package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ColorEntity;

public interface IColorService {

    ColorEntity findColorByColorLink(String colorLink);

    ColorEntity createNewColor(ColorEntity colorEntity);
}
