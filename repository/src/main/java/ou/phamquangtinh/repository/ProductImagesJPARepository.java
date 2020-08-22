package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.util.Optional;

public interface ProductImagesJPARepository extends JpaRepository<ProductImagesEntity,Long> {
    Optional<ProductImagesEntity> findByImageLink(String imageLink);
}
