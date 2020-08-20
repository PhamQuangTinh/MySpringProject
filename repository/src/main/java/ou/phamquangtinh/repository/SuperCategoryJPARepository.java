package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.util.Optional;

public interface SuperCategoryJPARepository extends JpaRepository<SuperCategoryEntity,Long> {

    Optional<SuperCategoryEntity> findByName(String name);
}
