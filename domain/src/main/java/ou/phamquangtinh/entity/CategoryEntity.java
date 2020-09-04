package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(value = {"subCategoryEntities","superCategoryEntity","productEntities"})
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
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProductEntity> productEntities;

}
