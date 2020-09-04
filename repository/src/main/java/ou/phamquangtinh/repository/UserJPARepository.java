package ou.phamquangtinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Transactional
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findByRoles_Code(String code, Pageable pageable);

    Streamable<UserEntity> findByLastNameContaining(String lastName);

    Page<UserEntity> findByFirstNameOrLastNameContaining(String firstName, String lastName, Pageable pageable);


}
