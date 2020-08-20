package ou.phamquangtinh.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sub_categories")
public class SubCategoryEntity extends BaseEntity<String> {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT",nullable = true)
    private String description;

    @OneToMany(mappedBy = "subCategoryEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> productEntities;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoryEntity categoryEntity;
}
