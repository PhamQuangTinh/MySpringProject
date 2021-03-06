package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductKey;

import java.util.Optional;

public interface AvailableProductJPARepository extends JpaRepository<AvailableProductEntity, AvailableProductKey> {

    Optional<AvailableProductEntity> findById_ProductIdAndId_ColorIdAndId_SizeId(Long proId, Long colorId, Long sizeId);

}
