package ou.phamquangtinh.entity.user;


import lombok.*;
import ou.phamquangtinh.entity.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    //mappedBy trỏ tới tên biến roles ở trong User
    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<User> users;
}
