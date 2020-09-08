package ou.phamquangtinh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties(value = {"orders","comments","roles","password","accessToken","refreshToken","phone","email"})
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

    @Column(nullable = true, columnDefinition = "TEXT")
    private String accessToken;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String refreshToken;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderEntity> orders;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductCommentEntity> comments;

    @ManyToMany(mappedBy = "users")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<RoleEntity> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "user_like_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductEntity> likeProductEntities;
}