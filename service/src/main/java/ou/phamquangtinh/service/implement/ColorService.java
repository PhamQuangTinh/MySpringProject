package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.repository.ColorJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IColorService;

import java.util.Collection;
import java.util.HashSet;
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
    public ColorEntity createNewOrUpdateColor(ColorEntity colorEntity) {
        return colorJPARepository.saveAndFlush(colorEntity);
    }

    @Override
    public ColorEntity getColorToUpdate(Long id) {
        return colorJPARepository.getOne(id);
    }

    @Override
    public ColorEntity addNewProductImage(Long colorId, ProductImagesEntity productImagesEntity) {

        ColorEntity colorEntity = colorJPARepository.getOne(colorId);
        if(colorEntity.getProductImagesEntities() == null){
            Collection<ProductImagesEntity> productImagesEntities = new HashSet<>();
            productImagesEntities.add(productImagesEntity);
            colorEntity.setProductImagesEntities(productImagesEntities);
        }else{
            colorEntity.getProductImagesEntities().add(productImagesEntity);
        }
        ColorEntity colorEntityRes = colorJPARepository.saveAndFlush(colorEntity);

        return colorEntity;

    }

    @Override
    public ColorEntity addNewAvailableProduct(Long colorId, AvailableProductsEntity availableProductsEntity) {
        ColorEntity colorEntity = colorJPARepository.getOne(colorId);

        if(colorEntity.getAvailableProductsEntities() == null){
            Collection<AvailableProductsEntity> availableProductsEntities = new HashSet<>();
            availableProductsEntities.add(availableProductsEntity);
            colorEntity.setAvailableProductsEntities(availableProductsEntities);
        }else{
            colorEntity.getAvailableProductsEntities().add(availableProductsEntity);
        }
        ColorEntity colorRes = colorJPARepository.saveAndFlush(colorEntity);

        return colorRes;
    }
}
