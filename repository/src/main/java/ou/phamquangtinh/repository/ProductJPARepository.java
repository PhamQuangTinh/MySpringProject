package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductEntity;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

}
