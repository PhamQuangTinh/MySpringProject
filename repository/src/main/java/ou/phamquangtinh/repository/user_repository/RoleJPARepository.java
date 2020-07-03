package ou.phamquangtinh.repository.user_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.user.Role;

public interface RoleJPARepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
