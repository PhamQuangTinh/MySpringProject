package ou.phamquangtinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.ColorEntity;

import java.util.Optional;

public interface ColorJPARepository extends JpaRepository<ColorEntity,Long> {

    Optional<ColorEntity> findByColorLink(String colorLink);

    ColorEntity findByColorLinkContaining(String colorLink);

    Page<ColorEntity> findByProductColorEntities_Id_ProductId(Long Id, Pageable page);
}
