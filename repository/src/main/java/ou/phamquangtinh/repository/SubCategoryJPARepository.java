package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;

import java.util.Optional;

public interface SubCategoryJPARepository extends JpaRepository<SubCategoryEntity,Long> {

    Optional<SubCategoryEntity> findByName(String name);

    Optional<SubCategoryEntity> findByProductEntities_Id(Long id);

}
