package ou.phamquangtinh.repository.user_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
