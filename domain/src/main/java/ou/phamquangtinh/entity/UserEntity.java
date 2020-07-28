package ou.phamquangtinh.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ou.phamquangtinh.entity.middle_entity.CommentEntity;

import javax.persistence.*;
import java.util.Collection;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity<String> {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderEntity> orders;

    @OneToMany(mappedBy = "userEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentEntity> comments;

    @OneToMany(mappedBy = "userEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CartEntity> carts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH
            , CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude //Không sử dụng trường này trong hashcode và equals
    @ToString.Exclude //Không sử dụng trường này trong toString()
    @JoinTable(name = "user_role", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name = "user_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(User)
            inverseJoinColumns = @JoinColumn(name = "role_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(Role)
    )
    private Collection<RoleEntity> roles;
}