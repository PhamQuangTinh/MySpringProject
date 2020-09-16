package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.repository.ColorJPARepository;
import ou.phamquangtinh.service.component_service.IColorService;
import ou.phamquangtinh.service.util.CommonUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorJPARepository colorJPARepository;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public ColorEntity findColorByColorLink(String colorLink) {
        Optional<ColorEntity> colorEntity = colorJPARepository.findByColorLink(colorLink);
        return colorEntity.orElse(null);
    }

    @Override
    public ColorEntity findColorByColorLinkContaining(String colorLink) {
        return colorJPARepository.findByColorLinkContaining(colorLink);
    }

    @Override
    public ColorEntity createNewOrUpdateColor(ColorEntity colorEntity) {
        return colorJPARepository.saveAndFlush(colorEntity);
    }

    @Override
    public ColorEntity getColorToUpdate(Long id) {
        return colorJPARepository.getOne(id);
    }

    @Override
    public ColorEntity addNewProductColor(Long colorId, ProductColorEntity productColorEntity) {

        ColorEntity colorEntity = colorJPARepository.getOne(colorId);
        if(colorEntity.getProductColorEntities() == null){
            Collection<ProductColorEntity> productColorEntities = new HashSet<>();
            productColorEntities.add(productColorEntity);
            colorEntity.setProductColorEntities(productColorEntities);
        }else{
            colorEntity.getProductColorEntities().add(productColorEntity);
        }
        return colorJPARepository.saveAndFlush(colorEntity);

    }

    @Override
    public ListResponsePagination findColorByProductId(Long proId, int page, int size) {
        Sort sort = Sort.by("colorName");
        Pageable pageable = PageRequest.of(page - 1, size , sort);

        Page<ColorEntity> res = colorJPARepository.findByProductColorEntities_Id_ProductId(proId, pageable);

        return commonUtil.getListResponsePagination(res);
    }


}
