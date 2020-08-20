package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

public interface ProductImagesJPARepository extends JpaRepository<ProductImagesEntity,Long> {

}
