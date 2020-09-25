package ou.phamquangtinh.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.OderDetailKey;

import java.util.List;

public interface OrderDetailJPARepository extends JpaRepository<OrderDetailEntity, OderDetailKey> {
    List<OrderDetailEntity> findByOrderEntity_Id(Long id);
}
