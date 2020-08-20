package ou.phamquangtinh.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "super_category")
public class SuperCategoryEntity extends BaseEntity<String>{

    @Column
    private String name;

    @Column(columnDefinition = "TEXT",nullable = true)
    private String description;

    @OneToMany(mappedBy = "superCategoryEntity",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CategoryEntity> categoryEntities;


}
