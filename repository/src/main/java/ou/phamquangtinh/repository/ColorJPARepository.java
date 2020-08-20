package ou.phamquangtinh.repository;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.util.Optional;

public interface ColorJPARepository extends JpaRepository<ColorEntity,Long> {

    Optional<ColorEntity> findByColorLink(String colorLink);
}
