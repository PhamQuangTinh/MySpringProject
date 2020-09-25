package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.OrderEntity;

import java.util.Optional;

public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findById(Long id);
}
