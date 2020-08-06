package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.OrderEntity;

public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {
}
