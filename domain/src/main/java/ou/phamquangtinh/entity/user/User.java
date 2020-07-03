package ou.phamquangtinh.entity.user;// User.java
import java.util.Collection;

import javax.persistence.*;

import lombok.*;
import ou.phamquangtinh.entity.BaseEntity;


@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "User")
public class User extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude //Không sử dụng trường này trong hashcode và equals
    @ToString.Exclude //Không sử dụng trường này trong toString()
    @JoinTable(name ="user_role", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name="user_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(User)
            inverseJoinColumns = @JoinColumn(name = "role_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(Role)
    )
    private Collection<Role> roles;


}