package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;

import java.util.Optional;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductName(String productName);

    Optional<ProductEntity> findByCategoryEntities_Id(Long id);
}
