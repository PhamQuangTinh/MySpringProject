package ou.phamquangtinh.repository.user_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.RoleEntity;

public interface RoleJPARepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(String code);
}
