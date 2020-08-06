package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.CategoryEntity;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Long> {
}
