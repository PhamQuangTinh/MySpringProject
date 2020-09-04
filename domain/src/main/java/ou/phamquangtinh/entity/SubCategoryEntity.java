package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sub_categories")
@JsonIgnoreProperties(value = {"productEntities","categoryEntity"})
public class SubCategoryEntity extends BaseEntity<String> {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT",nullable = true)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "subcategory_product", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name = "sub_category_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(User)
            inverseJoinColumns = @JoinColumn(name = "product_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(Role)
    )
    private Collection<ProductEntity> productEntities;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoryEntity categoryEntity;
}
