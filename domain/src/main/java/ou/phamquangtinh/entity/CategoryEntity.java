package ou.phamquangtinh.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "category")
@Entity
public class CategoryEntity extends BaseEntity<String> {

    @Column
    private String categoryName;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "categoryEntity")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<SubCategoryEntity> subCategoryEntities;

    @ManyToOne
    @JoinColumn(name = "super_category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SuperCategoryEntity superCategoryEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "category_product", // Tạo một join table tên là "user_role"
            joinColumns = @JoinColumn(name = "category_id"), //Trong đó, khóa ngoại chính là user_id trỏ tới class hiện tại(User)
            inverseJoinColumns = @JoinColumn(name = "product_id") //khóa ngoại thứ 2 là role_id trỏ tới thuộc tính của bảng còn lại(Role)
    )
    private Collection<ProductEntity> productEntities;

}
