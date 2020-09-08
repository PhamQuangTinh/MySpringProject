package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.ProductColorKey;

import java.util.List;
import java.util.Optional;

public interface ProductColorJPARepository extends JpaRepository<ProductColorEntity, ProductColorKey> {
    List<ProductColorEntity> findById_ProductId(Long proId);

    Optional<ProductColorEntity> findById_ProductIdAndId_ColorId(Long proId, Long colorId);
}
