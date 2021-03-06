package ou.phamquangtinh.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.request.CreateNewProductResquest;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.ProductInfoResponse;
import ou.phamquangtinh.dto.response.model.ColorModel;
import ou.phamquangtinh.dto.response.model.ProductImagesModel;
import ou.phamquangtinh.dto.response.model.SizeModel;
import ou.phamquangtinh.entity.*;
import ou.phamquangtinh.entity.middle_entity.*;
import ou.phamquangtinh.repository.ProductJPARepository;
import ou.phamquangtinh.service.component_service.IProductColorService;
import ou.phamquangtinh.service.component_service.IProductService;
import ou.phamquangtinh.service.util.CommonUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductJPARepository productJPARepository;

    @Autowired
    private IProductColorService productColorService;


    @Autowired
    private CommonUtil commonUtil;

    @Override
    public ProductEntity findProductByProductName(String productName) {
        return productJPARepository.findByProductName(productName).orElse(null);
    }

    @Override
    public ProductEntity createNewOrUpdateProduct(ProductEntity productEntity) {
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity findProductById(Long id) {
        Optional<ProductEntity> productEntity = productJPARepository.findById(id);
        return productEntity.orElse(null);
    }

    @Override
    public ProductEntity getProductToUpdate(Long id) {
        return productJPARepository.getOne(id);
    }

    @Override
    public ProductEntity addNewCategory(Long productId, CategoryEntity categoryEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);

        if(productEntity.getCategoryEntities() == null){
            Collection<CategoryEntity> categoryEntityCollection = new HashSet<>();
            categoryEntityCollection.add(categoryEntity);
            productEntity.setCategoryEntities(categoryEntityCollection);
        }else{
            Collection<CategoryEntity> collection = productEntity.getCategoryEntities();
            if(collection.contains(categoryEntity)){
                System.out.println("EXIST CATEGORY " + categoryEntity.getCategoryName() + " IN PRODUCT " + productEntity.getProductName());
                return null;
            }
            productEntity.getCategoryEntities().add(categoryEntity);
        }
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity addNewProductAvatar(Long productId, ProductAvatarEntity productAvatarEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);

        if(productEntity.getProductAvatarEntities() == null){
            List<ProductAvatarEntity> productAvatarEntities = new ArrayList<>();
            productAvatarEntities.add(productAvatarEntity);
            productEntity.setProductAvatarEntities(productAvatarEntities);
        }else{
            productEntity.getProductAvatarEntities().add(productAvatarEntity);
        }

        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity addNewProductColor(Long productId, ProductColorEntity productColorEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);
        if(productEntity.getProductColorEntities() == null){
            Collection<ProductColorEntity> productColorEntities = new HashSet<>();
            productColorEntities.add(productColorEntity);
            productEntity.setProductColorEntities(productColorEntities);
        }else{
            productEntity.getProductColorEntities().add(productColorEntity);
        }
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ListResponsePagination getAllProductPagination(int page, int size, String sortBy) {
        Sort sort = commonUtil.getSort(sortBy);

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findAll(pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }


    @Override
    public ProductEntity addNewSubCategory(Long productId, SubCategoryEntity subCategoryEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);
        if(productEntity.getSubCategoryEntity() == null){
            Collection<SubCategoryEntity> subCategoryEntities = new HashSet<>();
            subCategoryEntities.add(subCategoryEntity);
            productEntity.setSubCategoryEntity(subCategoryEntities);
        }else{
            Collection<SubCategoryEntity> collection = productEntity.getSubCategoryEntity();
            if(collection.contains(subCategoryEntity)){
                System.out.println("EXIST SUB CATEGORY " + subCategoryEntity.getName() + " IN PRODUCT " + productEntity.getProductName());
                return null;
            }
            productEntity.getSubCategoryEntity().add(subCategoryEntity);

        }
        return productJPARepository.saveAndFlush(productEntity);

    }

    //Tìm kiếm sản phẩm theo category Id
    @Override
    public ListResponsePagination findProductByCategoryId(Long cateId, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findByCategoryEntities_Id(cateId,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductByCategoryName(String categoryName, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findByCategoryEntities_CategoryName(categoryName,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductBySubCategoryId(Long subCateId, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findBySubCategoryEntity_Id(subCateId,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductBySubCategoryName(String subCategoryName, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findBySubCategoryEntity_Name(subCategoryName,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductByColorId(Long colorId, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findByProductColorEntities_Id_ColorId(colorId,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductBySizeId(Long sizeId, int page, int size) {
        Sort sort = Sort.by("productName");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository.findByProductColorEntities_AvailableProductEntities_Id_SizeId(sizeId,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductBySexTypeAndUnitPrice(Set<String> sexType, double fPrice,
                                                                   double lPrice, String colorName, int page, int size, String sortBy) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProductEntity> pageRes = null;
        if(colorName.equals("")){
            pageRes = productJPARepository.findBySexTypeInAndUnitPriceBetween(sexType, fPrice, lPrice, pageable);
        }else{
            pageRes = productJPARepository
                    .findBySexTypeInAndUnitPriceBetweenAndProductColorEntities_ColorEntity_ColorNameContaining(sexType,fPrice,lPrice,colorName,pageable);
        }

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ProductEntity createProductAdmin(CreateNewProductResquest request) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(request.getProductName());
        productEntity.setUnitPrice(request.getUnitPrice());
        productEntity.setSexType(request.getSexType());
        productEntity.setDiscount(request.getDiscount());
        productEntity.setDescription(request.getDescription());
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity updateProductAdmin(CreateNewProductResquest request) {
        ProductEntity productEntity = getProductToUpdate(request.getId());
        productEntity.setProductName(request.getProductName());
        productEntity.setUnitPrice(request.getUnitPrice());
        productEntity.setSexType(request.getSexType());
        productEntity.setDiscount(request.getDiscount());
        productEntity.setDescription(request.getDescription());
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public void deleteProductAdmin(Long proid) {
        productJPARepository.deleteById(proid);
    }

    @Override
    public List<ProductEntity> findTop12ProductsByCategory(Set<String> cates) {
        return productJPARepository.findTop12ByCategoryEntities_CategoryNameInOrSubCategoryEntity_NameIn(cates,cates);
    }

    @Override
    public ListResponsePagination findProductBySexTypeAndCategory(String sType, String categoryName, int page, int size, String sortBy) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProductEntity> pageRes = null;
        List<Long> ids = new ArrayList<>();
        switch (categoryName) {
            case "T-shirts":
                ids.add(93L);
                ids.add(110L);
                ids.add(112L);
                break;
            case "Jeans":
                ids.add(109L);
                ids.add(98L);
                break;
            case "Underwear":
                ids.add(114L);
                break;
            case "Accessories":
                ids.add(117L);

                break;
            case "New Season":
                ids.add(87L);
                break;
            case "Jackets & coats":
                ids.add(88L);
                break;
        }
        if(ids.isEmpty()){
            pageRes = productJPARepository
                    .findBySexTypeIgnoreCase(sType, pageable);
        }else{
            pageRes = productJPARepository.findBySexTypeIgnoreCaseAndSubCategoryEntity_CategoryEntity_IdInOrSexTypeIgnoreCaseAndCategoryEntities_IdIn(sType, ids, sType, ids, pageable);
        }
        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public List<ProductEntity> findTop10SuperCategoryProduct(String categoryName, String sType) {
        return productJPARepository.findTop10BySexTypeAndCategoryEntities_SuperCategoryEntity_NameIgnoreCase(sType, categoryName);
    }

    @Override
    public ListResponsePagination findProductBySexType(String sType, int page, int size, String sortBy) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository
                .findBySexTypeIgnoreCase(sType, pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findListLikeProduct(int page, int size, String sortBy, Long userId) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository
                .findByProductLikeByUserEntities_Id(userId,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public ListResponsePagination findProductByProductNamePagination(int page, int size, String sortBy, String keyword) {
        Sort sort = commonUtil.getSort(sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ProductEntity> pageRes = productJPARepository
                .findByProductNameContaining(keyword,pageable);

        return commonUtil.getListResponsePagination(pageRes);
    }

    @Override
    public List<ProductEntity> findProductProductNameOrDescription(String keyword) {
        return productJPARepository.findTop10ByProductNameContaining(keyword);
    }

    @Override
    public List<ProductEntity> findTop10LikeProduct(Long userId) {
        return productJPARepository.findTop10ByProductLikeByUserEntities_Id(userId);
    }

    @Override
    public ProductInfoResponse getProductInfo(Long proId, Long userId) {
        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        ProductEntity productEntity = findProductById(proId);
        ModelMapper modelMapper = new ModelMapper();
        productInfoResponse = modelMapper.map(productEntity, ProductInfoResponse.class);

        List<ColorModel> colors = new ArrayList<>();
        List<SizeModel> sizes = new ArrayList<>();
        List<ProductImagesModel> firstImgesColor = new ArrayList<>();
        for (ProductColorEntity productColorEntity : productEntity.getProductColorEntities()) {
            ColorModel colorModel = new ColorModel(productColorEntity.getColorEntity().getId(),
                    productColorEntity.getColorEntity().getColorName(), productColorEntity.getColorEntity().getColorLink());
            colors.add(colorModel);
        }
        List<ProductColorEntity> productColorEntities = productColorService.findProductColorByProductId(proId);
        for (AvailableProductEntity availableProductEntity : productColorEntities.get(0).getAvailableProductEntities()) {
            SizeModel sizeModel = new SizeModel(availableProductEntity.getSizeEntity().getId(),
                    availableProductEntity.getSizeEntity().getSizeType());
            sizes.add(sizeModel);
        }
        for (ProductColorEntity productColorEntity : productColorEntities) {
            if(colors.get(0).getId().equals(productColorEntity.getId().getColorId())){
                for (ProductImagesEntity productImagesEntity : productColorEntity.getProductImagesEntities()) {
                    ProductImagesModel model = new ProductImagesModel(productImagesEntity.getId(), productImagesEntity.getImageLink());
                    firstImgesColor.add(model);
                }
                break;
            }

        }
        productInfoResponse.setCategory(productEntity.getCategoryEntities().stream().map(CategoryEntity::getCategoryName).collect(Collectors.toList()));
        for(SubCategoryEntity sub: productEntity.getSubCategoryEntity()){
            productInfoResponse.getCategory().add(sub.getName());
        }

        productInfoResponse.setColors(colors);
        productInfoResponse.setSize(sizes);
        productInfoResponse.setFirstImagesColor(firstImgesColor);
        if(userId == 0){
            productInfoResponse.setUserLikeProducts(false);
        }else{
            Optional<ProductEntity> productEntity1 = productJPARepository.findByIdAndProductLikeByUserEntities_Id(proId,userId);
            productInfoResponse.setUserLikeProducts(productEntity1.isPresent());
        }

        return productInfoResponse;

    }




    @Override
    public void addNewOrderDetail(Long proId, OrderDetailEntity orderDetailEntity) {
        ProductEntity productEntity = getProductToUpdate(proId);
        if(productEntity != null){
            if(productEntity.getOrderDetail() == null){
                Collection<OrderDetailEntity> orderDetailEntities = new HashSet<>();
                orderDetailEntities.add(orderDetailEntity);
                productEntity.setOrderDetail(orderDetailEntities);
            }else{
                productEntity.getOrderDetail().add(orderDetailEntity);
            }
            createNewOrUpdateProduct(productEntity);
        }
    }

    @Override
    public void addNewProductCommentDetail(Long proId, ProductCommentDetailEntity productCommentDetailEntity) {
        ProductEntity productEntity = getProductToUpdate(proId);
        if(productEntity.getProductCommentDetailEntities() == null){
            List<ProductCommentDetailEntity> productCommentDetailEntities = new ArrayList<>();
            productCommentDetailEntities.add(productCommentDetailEntity);
            productEntity.setProductCommentDetailEntities(productCommentDetailEntities);
        }
        else{
            productEntity.getProductCommentDetailEntities().add(productCommentDetailEntity);
        }
        createNewOrUpdateProduct(productEntity);
    }

}
