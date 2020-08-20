package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.SuperCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductsKey;

public interface AvailableProductJPARepository extends JpaRepository<AvailableProductsEntity, AvailableProductsKey> {
}
