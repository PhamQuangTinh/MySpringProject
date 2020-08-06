package ou.phamquangtinh.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @ManyToMany(mappedBy = "categorys")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> products;

}
