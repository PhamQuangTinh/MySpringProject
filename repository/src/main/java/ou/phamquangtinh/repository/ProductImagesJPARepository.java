package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.util.Optional;
import java.util.Set;

public interface ProductImagesJPARepository extends JpaRepository<ProductImagesEntity,Long> {
    Optional<ProductImagesEntity> findByImageLink(String imageLink);

    Set<ProductImagesEntity> findByProductColorEntity_Id_ProductId(Long proId);

    Set<ProductImagesEntity> findByProductColorEntity_Id_ProductIdAndProductColorEntity_Id_ColorId(Long proId, Long colorId);
}
