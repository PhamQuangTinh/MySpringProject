package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.CartEntity;

public interface CartJPARepository extends JpaRepository<CartEntity, Long> {
}
