package ou.phamquangtinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ou.phamquangtinh.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductName(String productName);

    Optional<ProductEntity> findById(Long id);

    Optional<ProductEntity> findByIdAndProductLikeByUserEntities_Id(Long proId, Long userId);


    Page<ProductEntity> findByUnitPriceBetween(double fPrice, double lPrice, Pageable page);

    Page<ProductEntity> findByCategoryEntities_CategoryName(String categoryName, Pageable page);

    Page<ProductEntity> findBySubCategoryEntity_Name(String subCateName, Pageable page);

    Page<ProductEntity> findByCategoryEntities_Id(Long id, Pageable page);

    Page<ProductEntity> findBySubCategoryEntity_Id(Long id, Pageable page);

    Page<ProductEntity> findByProductColorEntities_Id_ColorId(Long Id, Pageable page);

    Page<ProductEntity> findByProductColorEntities_AvailableProductEntities_Id_SizeId(Long sizeId, Pageable page);

    List<ProductEntity> findTop10ByProductNameContaining(String productName);

    List<ProductEntity> findTop10ByProductLikeByUserEntities_Id(Long userId);

    Page<ProductEntity> findBySexTypeInAndUnitPriceBetweenAndProductColorEntities_ColorEntity_ColorNameContaining
            (Set<String> sexTypes, double fPrice, double lPrice, String colorName, Pageable page);

    Page<ProductEntity> findByProductLikeByUserEntities_Id(Long id, Pageable page);

    Page<ProductEntity> findByProductNameContaining(String productName, Pageable page);

    Page<ProductEntity> findBySexTypeIgnoreCase(String sType, Pageable page);

    Page<ProductEntity> findBySexTypeIgnoreCaseAndSubCategoryEntity_CategoryEntity_IdInOrSexTypeIgnoreCaseAndCategoryEntities_IdIn(
            String sType,List<Long> ids, String sType1, List<Long> ids1, Pageable page);


    List<ProductEntity> findTop10BySexTypeAndCategoryEntities_SuperCategoryEntity_NameIgnoreCase(String sType, String superCategoryName);

    Page<ProductEntity> findBySexTypeInAndUnitPriceBetween(Set<String> sexType, double fPrice, double lPrice, Pageable page);

    List<ProductEntity> findTop12ByCategoryEntities_CategoryNameInOrSubCategoryEntity_NameIn(Set<String> cates, Set<String> subCates);


}
