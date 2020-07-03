package ou.phamquangtinh.repository.user_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ou.phamquangtinh.entity.user.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUserName(@Param("username") String username);
    @Query(value = "select * from User where lower(User.username) like " +
        "lower(concat('%',:username,'%'))", nativeQuery = true)
    Optional<User> findByUsername(String username);





}
