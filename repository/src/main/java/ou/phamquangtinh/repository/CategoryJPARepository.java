package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findBycategoryName(String categoryName);
}
