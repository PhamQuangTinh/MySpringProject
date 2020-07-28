package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.entity.UserEntity;

import java.util.Optional;

@Transactional
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);


}
