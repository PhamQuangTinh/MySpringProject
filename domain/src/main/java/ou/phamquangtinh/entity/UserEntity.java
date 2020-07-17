package ou.phamquangtinh.entity;// User.java

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "User")
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude //Không sử dụng trường này trong hashcode và equals
    @ToString.Exclude //Không sử dụng trường này trong toString()
    @JoinTable(name = "user_role", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name = "user_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(User)
            inverseJoinColumns = @JoinColumn(name = "role_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(Role)
    )
    private Collection<RoleEntity> roles;
}