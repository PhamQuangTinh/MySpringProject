package ou.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;

import java.util.Optional;

public interface UserCommentJPARepository extends JpaRepository<UserCommentEntity, Long> {
    Optional<UserCommentEntity> findById(Long id);
}
