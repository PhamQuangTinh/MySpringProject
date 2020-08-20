package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.util.Optional;

public interface SizeJPARepository extends JpaRepository<SizeEntity,Long> {

    Optional<SizeEntity> findBySizeType(String sizeType);
}
