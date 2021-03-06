package ou.phamquangtinh.service.implement;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.*;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductKey;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.ProductColorKey;
import ou.phamquangtinh.service.component_service.*;

import java.io.*;
import java.net.URL;
import java.util.*;

@Service
public class CrawlDataService {

    private final String webURL = "https://www.esprit.eu";
    private String finalURL = "";
    private String sexType = "";

    private final IProductService productService;

    private final IProductAvatarService productAvatarService;

    private final ISizeService sizeService;

    private final IColorService colorService;

    private final IProductImagesService productImagesService;

    private final IAvailableProductService availableProductService;

    private final ISuperCategoryService superCategoryService;

    private final ICategoryService categoryService;

    private final IProductColorService productColorService;

    private final ISubCategoryService subCategoryService;


    public CrawlDataService(IProductService productService, IProductAvatarService productAvatarService, ISizeService sizeService, IColorService colorService, IProductImagesService productImagesService, IAvailableProductService availableProductService, ISuperCategoryService superCategoryService, ICategoryService categoryService, IProductColorService productColorService, ISubCategoryService subCategoryService) {
        this.productService = productService;
        this.productAvatarService = productAvatarService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productImagesService = productImagesService;
        this.availableProductService = availableProductService;
        this.superCategoryService = superCategoryService;
        this.categoryService = categoryService;
        this.productColorService = productColorService;
        this.subCategoryService = subCategoryService;
    }


//    public void crawlDataSexType() {
//        try {
//
//            final Document document = Jsoup.connect(webURL).get();
//
//            Element element = document.selectFirst(".ocm-flex");
//            System.out.println(element.html());
//            Element getSecond = document.selectFirst("#main-navigation");
//
//            Elements getMenuItem = getSecond.select(".nav-item");
//
//            //Giữ lại
//            getMenuItem.remove(4);
//            getMenuItem.remove(4);
//            getMenuItem.remove(4);
//            getMenuItem.remove(getMenuItem.size() - 1);
//            getMenuItem.remove(0);
//
//
//            //Duyệt từng đối tượng trong menu lớn
//            for (Element e : getMenuItem) {
//
//                String folderURL = "C:\\Users\\Admin\\Desktop\\MySpringProject\\src\\main\\java\\shop-web\\src\\assets\\img";
//
//
//                //Lấy tên SEX TYPE
//                String sexTypeProduct = e.getElementsByTag("span").first().text();
//                sexType = sexTypeProduct;
//                sexTypeProduct = sexTypeProduct.replaceAll("[\\/:*?\"<>|]", "");
//                ;
//                //URL folder SEXTYPE
//                folderURL = folderURL + "\\" + sexTypeProduct;
//
//                //Tạo folder SEXTYPE
//                createFolder(folderURL);
//
//                //Đường link dẫn tới SUPER CATEGORY
//                String superCategoryLink = webURL + e.selectFirst("a[href]").attr("href");
//
//                //Tạo đường link dẫn tới SUPER CATEGORY
//                if (sexTypeProduct.equalsIgnoreCase("men") || sexTypeProduct.equalsIgnoreCase("women")) {
//                    superCategoryLink = superCategoryLink + "/clothes";
//                } else if (sexTypeProduct.equalsIgnoreCase("kids")) {
//                    superCategoryLink = superCategoryLink + "/girls";
//                } else {
//                    superCategoryLink = superCategoryLink + "/living";
//                }
////                System.out.println("************************************************************************************************************************************");
////                System.out.println("************************************************************************************************************************************");
//                System.out.println("*************************************" + "START ACCESSING " + sexTypeProduct + " PAGE: " + superCategoryLink + "**************************************");
////                System.out.println("************************************************************************************************************************************");
////                System.out.println("************************************************************************************************************************************");
//
//                crawlDataSuperCategory(superCategoryLink, folderURL);
//
////                System.out.println("************************************************************************************************************************************");
////                System.out.println("************************************************************************************************************************************");
//                System.out.println("*************************************" + "END ACCESSING " + sexTypeProduct + " PAGE: " + superCategoryLink + "**************************************");
////                System.out.println("************************************************************************************************************************************");
////                System.out.println("************************************************************************************************************************************");
////                System.out.println();
////                System.out.println();
////                System.out.println();
////                System.out.println();
////                System.out.println();
//
//            }
//
//        } catch (NullPointerException | IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void crawlDataSuperCategory(String link, String folder) {


    public void crawlDataSuperCategory() {
        try {
            Document document = Jsoup.connect("https://www.esprit.eu/kids/girls").get();
            String folder = "C:\\Users\\Admin\\Desktop\\MySpringProject\\src\\main\\java\\shop-web\\src\\assets\\img\\woman";
            //Menu toàn bộ SUPER CATEGORY của thẻ ul
            Element menuSuperCategory = document.selectFirst(".sidenavigation__level-1");

            //Lấy tất cả SUPER CATEGORY ủa ul > li
            Elements superCategories = menuSuperCategory.select(".sidenavigation__level-1 > li");
//            for (Element cate : superCategories) {
            if(superCategories.size() > 4){
                for (int i = 0; i < 2; i++) {

                    //SUPER CATEGORY NAME lưu vào database
                    String superCategoryDB = superCategories.get(i).getElementsByTag("a").first().text();
                    sexType = superCategoryDB;
                    superCategoryDB = superCategoryDB.replaceAll("[\\/:*?\"<>|]", "");


                    //URL SUPER CATEGORY
                    String folderSuperCategoryURL = folder + "\\" + superCategoryDB;

                    //Lưu Database
                    SuperCategoryEntity superCategoryEntity = superCategoryService.findSuperCategoryByName(superCategoryDB);
                    if (superCategoryEntity == null) {
                        superCategoryEntity = new SuperCategoryEntity();
                        superCategoryEntity.setName(superCategoryDB);
                        superCategoryEntity = superCategoryService.createNewOrUpdateSuperCategory(superCategoryEntity);
                    }

                    //Tạo folder SUPER CATEGORY
                    createFolder(folderSuperCategoryURL);

                    //Đường link cơ bản dẫn tới CATEGORY
                    String categoryLink = superCategories.get(i).getElementsByTag("a").attr("href");


                    System.out.println();
//                System.out.println("******************************************************************************************************************************");
                    System.out.println("*************************************START ACCESSING SUPER CATEGORY: " + superCategoryDB + "**************************************");
//                System.out.println("******************************************************************************************************************************");

                    //Tạo đường link tới CATEGORY
                    String fullLinkToCategory = webURL + categoryLink;
                    System.out.println("LINK SUPER CATEGORY: " + fullLinkToCategory);
                    crawlDataCategory(fullLinkToCategory, folderSuperCategoryURL, superCategoryEntity);

//                System.out.println("******************************************************************************************************************************");
                    System.out.println("*************************************END ACCESSING SUPER CATEGORY: " + superCategoryDB + "**************************************");
//                System.out.println("******************************************************************************************************************************");
                }
            }


        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }


    private void crawlDataCategory(String link, String folder, SuperCategoryEntity superCategory) {
        try {

            Document document = Jsoup.connect(link).get();

            //Menu toàn bộ CATEGORY của thẻ ul
            Element menuCategory = null;

            //Lấy tất cả CATEGORY ủa ul > li
            try {
                menuCategory = document.selectFirst(".sidenavigation__level-2");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            Elements categoryLink = menuCategory.select("li");

            if(categoryLink.size() > 3){
                for (int i = 4; i < 9; i++) {

                    //CATEGORY NAME lưu vào database
                    String categoryDBName = categoryLink.get(i).getElementsByTag("a").first().text();

                    if (categoryDBName.contains("SALE world")) {
                        continue;
                    }

                    categoryDBName = categoryDBName.replaceAll("[\\/:*?\"<>|]", "");

                    //URL CATEGORY
                    String folderCategoryURL = folder + "\\" + categoryDBName;

                    //DATABASE
                    CategoryEntity categoryEntity = categoryService.findCategoryByName(categoryDBName);

                    if(categoryEntity == null){
                        categoryEntity = new CategoryEntity();
                        categoryEntity.setCategoryName(categoryDBName);
                        categoryEntity.setSuperCategoryEntity(superCategory);
                        categoryEntity = categoryService.createNewOrUpdateCategory(categoryEntity);
//                        superCategoryService.addNewCategory(superCategory.getId(), categoryEntity);
                    }




                    //END DATABASE

                    //Tạo folder CATEGORY
                    createFolder(folderCategoryURL);

                    //Đường link cơ bản dẫn tới SUB CATEGORY
                    String subCategoryLink = categoryLink.get(i).getElementsByTag("a").attr("href");


//                System.out.println();
                    System.out.println("*********************************START ACCESSING CATEGORY: " + categoryDBName + "**********************************");

                    //Tạo đường link tới SUB CATEGORY
                    String fullLinkToSubCategory = webURL + subCategoryLink;
                    System.out.println("LINK TO CATEGORY: " + fullLinkToSubCategory);

                    crawlDataSubCategory(fullLinkToSubCategory, folderCategoryURL, categoryEntity);

                    System.out.println("***********************************END ACCESSING CATEGORY: " + categoryDBName + "************************************");

                }
            }



        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }


    private void crawlDataSubCategory(String link, String folder, CategoryEntity categoryEntity) {
        try {
            Document document = Jsoup.connect(link).get();

            //Menu toàn bộ SUB CATEGORY của thẻ ul
            Element menuSubCategory = null;

            try {
                menuSubCategory = document.selectFirst(".sidenavigation__level-3");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Nếu category không có sub category thì tiến hành lấy thông tinh sản phẩm
            if (menuSubCategory == null) {
                finalURL = folder;
                System.out.println("**********************************NO SUB CATEGORY*********************************");
                crawlDataProduct(link, categoryEntity.getId(), 1);
                return;
            }

            //Lấy tất cả SUB CATEGORY ủa ul > li
            Elements subCategory = menuSubCategory.select("li");
            if(subCategory.size() > 4){
                for (int i = 0; i < 3; i++) {

                    //SUB CATEGORY NAME lưu vào database
                    String subCategoryDBName = subCategory.get(i).getElementsByTag("a").first().text();

                    if (subCategoryDBName.contains("SALE world")) {
                        continue;
                    }
                    subCategoryDBName = subCategoryDBName.replaceAll("[\\/:*?\"<>|]", "");


                    //URL CATEGORY
                    String folderSubCategoryURL = folder + "\\" + subCategoryDBName;

                    //DATABASE
                    SubCategoryEntity subCategoryEntity = subCategoryService.findSubCategoryByName(subCategoryDBName);
                    if (subCategoryEntity == null) {
                        subCategoryEntity = new SubCategoryEntity();
                        subCategoryEntity.setName(subCategoryDBName);
                        subCategoryEntity.setCategoryEntity(categoryEntity);
                        subCategoryEntity = subCategoryService.createNewOrUpdateSubCategory(subCategoryEntity);

//                        categoryService.addNewSubCategory(categoryEntity.getId(), subCategoryEntity);

                    }

                    //END DATABASE
                    //Tạo folder CATEGORY
                    createFolder(folderSubCategoryURL);

                    finalURL = folderSubCategoryURL;

                    //Đường link cơ bản dẫn tới PRODUCTS
                    String productLink = subCategory.get(i).getElementsByTag("a").attr("href");


//                System.out.println();
                    System.out.println("*********************************START ACCESSING SUB CATEGORY: " + subCategoryDBName + "**********************************");

                    //Tạo đường link tới SUB CATEGORY
                    String fullLinkToSubCategory = webURL + productLink;
                    System.out.println("LINK TO SUB CATEGORY: " + fullLinkToSubCategory);

                    crawlDataProduct(fullLinkToSubCategory, subCategoryEntity.getId(), 2);

                    System.out.println("***********************************END ACCESSING SUB CATEGORY: " + subCategoryDBName + "************************************");

                }
            }



        } catch (NullPointerException | IOException ignored) {
        }
    }

    private void crawlDataProduct(String link, Long id, int code) {
        try {

            //Số trang của 1 page
            int pageNumber;
            Document document = Jsoup.connect(link).get();

            Elements pageNumberElements = null;
            try {
                pageNumberElements = document.select(".pagination__item");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (pageNumberElements == null) {
                pageNumber = 0;
            } else {
                pageNumber = pageNumberElements.size();
            }

            String page = link + "?pageNumber=";

            if (pageNumber == 0) {
                crawlDataProductInfo(link, id, code);
                System.out.println("********************************NO PAGINATION*********************************");
            } else if (pageNumber > 2) {
                System.out.println("PAGE NUMBER: " + pageNumber);
                //Duyệt từng page của 1 trang product
                for (int i = 1; i < 2; i++) {
                    page = page + i;
                    System.out.println("PAGE: " + page);

                    crawlDataProductInfo(page, id, code);

                    page = page.substring(0, page.length() - String.valueOf(i).length());
                }
            } else {
                System.out.println("PAGE NUMBER: " + pageNumber);
                //Duyệt từng page của 1 trang product
                for (int i = 1; i <= pageNumber; i++) {
                    page = page + i;
                    System.out.println("PAGE: " + page);

                    crawlDataProductInfo(page, id, code);

                    page = page.substring(0, page.length() - String.valueOf(i).length());
                }
            }


        } catch (Exception ignored) {
        }
    }

    //Lấy Thông tin sản phẩm
    private void crawlDataProductInfo(String link, Long id, int code) {


        try {
            Document document = Jsoup.connect(link).get();

            Element menuProduct = null;
            try {
                menuProduct = document.selectFirst(".product-overview .product-list");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (menuProduct == null) {
                return;
            }

            Elements productItems = menuProduct.children();

            //Duyệt qua từng sản phẩm
            productItems.forEach(element -> {
                Element linkToProductInfo = element.selectFirst(".product-details__style-name");

                String productNameAtPage = linkToProductInfo.text();

                ProductEntity productEntity = productService.findProductByProductName(productNameAtPage);
                if (productEntity != null) {
                    if (code == 1) {
                        CategoryEntity categoryEntity = categoryService.addNewProduct(id, productEntity);
                        if(categoryEntity != null){
                            productService.addNewCategory(productEntity.getId(), categoryEntity);
                            System.out.println("UPDATE PRODUCT " + productNameAtPage + " TO CATEGORY " + categoryEntity.getCategoryName() + " SUCCESS");
                        }
                    } else if (code == 2) {
                        SubCategoryEntity subCategoryEntity = subCategoryService.addNewProduct(id, productEntity);
                        if(subCategoryEntity != null){
                            productService.addNewSubCategory(productEntity.getId(), subCategoryEntity);
                            System.out.println("UPDATE PRODUCT " + productNameAtPage + " TO SUB_CATEGORY " + subCategoryEntity.getName() + " SUCCESS");
                        }
                    }
                } else {

                    String getProductInfoLink = linkToProductInfo.getElementsByTag("a").attr("href");

                    String finalLinkToProduct = webURL + getProductInfoLink;

                    System.out.println("PRODUCT " + finalLinkToProduct + ", " + "CREATE SUCCESS");

                    getProductInfo(finalLinkToProduct,code, id);
                }

            });


        } catch (Exception ignored) {

        }
    }

    private void getProductInfo(String link, int code, Long id) {
        try {
            String productName;
            String productPriceString = null;
            String productDescription;
            double productPrice;
            List<String> sizeProduct;
            List<String> avatarProduct = new ArrayList<>();
            Document document = Jsoup.connect(link).get();
            Map<String, List<String>> listProductColorImages = new HashMap<>();

            //Thông tin cơ bản của sản phẩm
            productName = document.selectFirst("h1.hc-headline-m").text();
            try {
                productPriceString = document.selectFirst("span.spv-price__selling").text();
            } catch (Exception ignored) {
            }
            if (productPriceString == null) {
                return;
            }
            productPrice = Double.parseDouble(productPriceString.substring(1));

            //Lấy tất cả màu ảnh
            Elements allProductsColor = document.select(".hc-color-thumb-container li");


            List<String> listProductImages;
            String colorName;
            for (int i = 0; i < allProductsColor.size(); i++) {
                if (i == 0) {
                    listProductImages = getImagesProduct(link);
                } else {
                    String s = "https:" + allProductsColor.get(i).getElementsByTag("a").attr("data-style-url");
                    listProductImages = getImagesProduct(s);
                }
                assert listProductImages != null;
                colorName = listProductImages.get(0);
                listProductImages.remove(0);

                listProductColorImages.put(colorName, listProductImages);

            }


            //Element size ảnh
            Element elementSizeProduct = null;
            try {
                elementSizeProduct = document.selectFirst("ul.pullDown");
                if (elementSizeProduct == null) {
                    elementSizeProduct = document.selectFirst("ul.sizeButtonList");
                }
            } catch (Exception ignored) {
            }

            //Lấy Size ảnh
            sizeProduct = getSizeProduct(elementSizeProduct);


            //Lấy Element  mô tả sản phẩm
            Element productDescriptionElement = document.selectFirst(".detailInformationContent__infoArea");

            //Mô tả sản phẩm String
            productDescription = getProductDescription(productDescriptionElement);


//            System.out.println("PROUCT NAME: " + productName);
            SizeEntity sizeEntity = null;
            ColorEntity colorEntity = null;
            ProductColorEntity productColorEntity = null;
            ProductEntity productEntity = productService.findProductByProductName(productName);
            if (productEntity == null) {
                productEntity = new ProductEntity();
                productEntity.setProductName(productName);
                productEntity.setUnitPrice(productPrice);
                productEntity.setSexType(sexType);
                productEntity.setDescription(productDescription);
                productEntity = productService.createNewOrUpdateProduct(productEntity);
                if (code == 1) {
                    productEntity = productService.addNewCategory(productEntity.getId(), categoryService.getCategoryToUpdate(id));
                    categoryService.addNewProduct(id, productEntity);
                } else if(code == 2) {
                    productEntity = productService.addNewSubCategory(productEntity.getId(), subCategoryService.getSubCategoryToUpdate(id));
                    subCategoryService.addNewProduct(id, productEntity);
                }


                //DATABASE
                Random random = new Random();


                String colorNameRes = "";
                for (Map.Entry<String, List<String>> entry : listProductColorImages.entrySet()) {

//                    System.out.println("COLOR: " + entry.getKey());


                    colorNameRes = entry.getKey();

                    //Tạo màu ảnh

                    ProductImagesEntity productImagesEntity = null;
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        String image = entry.getValue().get(i);
                        String imageLinkDB = finalURL.substring(65) + "\\" + image.substring(image.lastIndexOf("/") + 1, image.lastIndexOf("?")) + ".jfif";
                        String imageLinkWeb = "https:" + image;

                        if (i == 0 || i == 1) {
                            if (i == 0) {
                                colorEntity = colorService.findColorByColorLink(imageLinkDB);
                                if (colorEntity == null) {
                                    colorEntity = new ColorEntity();
                                    colorEntity.setColorName(colorNameRes);
                                    colorEntity.setColorLink(imageLinkDB);
                                    colorEntity = colorService.createNewOrUpdateColor(colorEntity);
                                }
                                productColorEntity = new ProductColorEntity();
                                ProductColorKey productColorKey = new ProductColorKey(productEntity.getId(), colorEntity.getId());
                                productColorEntity = new ProductColorEntity();
                                productColorEntity.setId(productColorKey);
                                productColorEntity = productColorService.createOrUpdateProductColor(productColorEntity);
                            }

                            ProductAvatarEntity productAvatarEntity1 = new ProductAvatarEntity();
                            productAvatarEntity1.setImageLink(imageLinkDB);
                            productAvatarEntity1.setProductEntity(productEntity);

                            productAvatarEntity1 = productAvatarService.createNewProductAvatar(productAvatarEntity1);
                            productService.addNewProductAvatar(productEntity.getId(), productAvatarEntity1);
                        }
                        downloadImage(imageLinkWeb);

                        if (i == 0) {
                            continue;
                        }
                        productImagesEntity = new ProductImagesEntity();
                        productImagesEntity.setImageLink(imageLinkDB);
                        productImagesEntity.setProductColorEntity(productColorEntity);
                        productImagesEntity = productImagesService.createNewOrUpdateProductImages(productImagesEntity);
                        productColorService.addNewProductImages(productColorEntity.getId(), productImagesEntity);
                    }

                    for (String size : sizeProduct) {
                        sizeEntity = sizeService.findSizeBySizeType(size);
                        if (sizeEntity == null) {
                            sizeEntity = new SizeEntity();
                            sizeEntity.setSizeType(size);
                            sizeService.createNewOrUpdateSize(sizeEntity);
                        }


                        AvailableProductKey availableProductsKey = new AvailableProductKey(productEntity.getId(), colorEntity.getId(), sizeEntity.getId());
                        int unitInOrder = random.nextInt(10);
                        int unitInStock = unitInOrder + random.nextInt(30) + 10;
                        AvailableProductEntity availableProductsEntity = new AvailableProductEntity(availableProductsKey, unitInOrder, unitInStock, productColorEntity, sizeEntity);

                        AvailableProductEntity availableProductsEntityRes = availableProductService.createNewOrUpdateAvailableProduct(availableProductsEntity);
                        productColorService.addNewAvailableProduct(productColorEntity.getId(), availableProductsEntity);
                        sizeService.addNewAvailableProduct(sizeEntity.getId(), availableProductsEntityRes);

                    }
                }


            }
//
//            if (code == 1) {
//                CategoryEntity categoryEntity = categoryService.addNewProduct(id, productEntity);
//                productService.addNewCategory(productEntity.getId(), categoryEntity);
//            } else if (code == 2) {
//                SubCategoryEntity subCategoryEntity = subCategoryService.addNewProduct(id, productEntity);
//                productService.addNewSubCategory(productEntity.getId(), subCategoryEntity);
//            }


            //END DATABASE
            System.out.println("****************************************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getImagesProduct(String link) {
        try {
            List<String> listColorImagesProduct = new ArrayList<>();
            Document document = Jsoup.connect(link).get();

            //Element màu ảnh đầu tiên
            Element firstProductColors = document.selectFirst(".spv-color-chooser-wrapper");

            //Màu ảnh đầu tiên
            String firstColor = firstProductColors.select(".hc-text-l").text();

            //Link tải màu ảnh đầu tiên
            String firstColorLink = firstProductColors.selectFirst("div.hc-color-thumb > img").attr("src");

            listColorImagesProduct.add(firstColor);
            listColorImagesProduct.add(firstColorLink);
            //Elemnts chứa tát cả các ảnh
            Elements productColorImagesElement = document.select("div.swiper-wrapper div.swiper-slide");

            //hình đầu tiên của màu ảnh đầu tiên
            String firstImage = productColorImagesElement.first().getElementsByTag("img").attr("src");
            listColorImagesProduct.add(firstImage);
            productColorImagesElement.remove(0);
            //Lấy tất cả các hình của màu ảnh đầu tiên
            productColorImagesElement.forEach(element -> listColorImagesProduct.add(element.getElementsByTag("img").attr("data-src")));
            return listColorImagesProduct;

        } catch (Exception e) {
            return null;
        }
    }

    private List<String> getSizeProduct(Element element) {
        List<String> res = new ArrayList<>();
        try {
            Elements listSizeProduct = element.getElementsByTag("li");
            listSizeProduct.forEach(e -> {
                String size = "";
                if (checkExistElement(e, ".sizeButtonList__itemSoldOutButtonSize")) {
                    size = e.selectFirst(".sizeButtonList__itemSoldOutButtonSize").text();
                } else if (checkExistElement(e, ".pullDown__itemSoldOutButtonSize")) {
                    size = e.selectFirst(".pullDown__itemSoldOutButtonSize").text();
                } else {
                    size = e.getElementsByTag("span").first().text();
                }
                res.add(size);
            });
        } catch (Exception e) {
            if (res.isEmpty()) {
                res.add("ONE SIZE");
            }
        }

        return res;


    }

    private String getProductDescription(Element element) {
        try {
            StringBuilder productDescription = new StringBuilder();
            String header = element.selectFirst("header h4").text();
            productDescription = new StringBuilder("<h4>" + header + "<h4>\n");
            Elements descriptionText = element.selectFirst(".hc-text-l").children();
            for (Element elementChild : descriptionText) {
                if (checkExistElement(elementChild, "h4")) {
                    productDescription.append("<h4>").append(elementChild.getElementsByTag("h4").text()).append("<h4>\n");
                }
                if (checkExistElement(elementChild, "ul")) {
                    Elements elements = elementChild.getElementsByTag("li");
                    for (Element element1 : elements) {
                        if (element1.getElementsByTag("li").text().equals("")) {
                            continue;
                        }
                        productDescription.append("<p>").append(element1.getElementsByTag("li").text()).append("<p>\n");
                    }
                }
            }
            return productDescription.toString();
        } catch (Exception e) {
            return "";
        }

    }

    private boolean checkExistElement(Element element, String clazz) {
        try {
            String res = element.selectFirst(clazz).html();
            return (res != null);
        } catch (Exception e) {
            return false;
        }
    }

//    private String subStringToSaveImage(String firstString){
//        String s = firstString.substring(firstString.lastIndexOf("/") + 1, firstString.lastIndexOf("?")) + ".jfif";
//        s = finalURL.substring(65) + s;
//        return s;
//    }

    private void downloadImage(String strImageURL) {

        //get file name from image path
        String strImageName =
                strImageURL.substring(strImageURL.lastIndexOf("/") + 1, strImageURL.lastIndexOf("?")) + ".jfif";

//        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
//        System.out.println(finalURL);

        try {

            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream(finalURL + "\\" + strImageName);

            //write bytes to the output stream
            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();

//            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void createFolder(String path) {
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdirs();
        if (bool) {
            System.out.println("Directory " + path + ": created successfully");
        } else {
            System.out.println("Couldn't create directory: " + path);
        }
    }

}