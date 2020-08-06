package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ShipperEntity;

public interface ShipperJPARepository extends JpaRepository<ShipperEntity, Long> {
}
