package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findById(Long id);

    List<OrderEntity> findByUserEntity_Id(Long userId);
}
