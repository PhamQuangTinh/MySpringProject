package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    //mappedBy trỏ tới tên biến roles ở trong User
    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude //Không sử dụng trường này trong hashcode và equals
    @ToString.Exclude //Không sử dụng trường này trong toString()
    @JoinTable(name = "user_role", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name = "role_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(Role)
            inverseJoinColumns = @JoinColumn(name = "user_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(User)
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<UserEntity> users;
}
