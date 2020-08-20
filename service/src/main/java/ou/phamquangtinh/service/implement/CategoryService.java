package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.repository.CategoryJPARepository;
import ou.phamquangtinh.service.component_service.ICategoryService;

import java.util.Optional;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryJPARepository categoryJPARepository;
    @Override
    public CategoryEntity createNewCategory(CategoryEntity categoryEntity) {
        return categoryJPARepository.saveAndFlush(categoryEntity);
    }

    @Override
    public CategoryEntity findCategoryByName(String name) {
        Optional<CategoryEntity> categoryEntity = categoryJPARepository.findBycategoryName(name);

        return categoryEntity.orElse(null);
    }
}
