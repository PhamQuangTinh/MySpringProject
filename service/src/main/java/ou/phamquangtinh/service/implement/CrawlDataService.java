package ou.phamquangtinh.service.implement;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.SuperCategoryEntity;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CrawlDataService {

    private final Set<String> categoryNames = new HashSet<String>();
    private final String webURL = "https://www.esprit.eu";
    private String finalURL = "";
    private String sexType = "";
    private final ProductService productService;

    private final ProductAvatarService productAvatarService;

    private final SizeService sizeService;

    private final ColorService colorService;

    private final ProductImagesService productImagesService;

    private final AvailableProductService availableProductService;

    private final SuperCategoryService superCategoryService;

    private final CategoryService categoryService;

    private final SubCategoryService subCategoryService;

    {
        categoryNames.add("Magazine");
        categoryNames.add("#Throwback collection");
        categoryNames.add("Accessories");
    }

    public CrawlDataService(ProductService productService, ProductAvatarService productAvatarService, SizeService sizeService, ColorService colorService, ProductImagesService productImagesService, AvailableProductService availableProductService, SuperCategoryService superCategoryService, CategoryService categoryService, SubCategoryService subCategoryService) {
        this.productService = productService;
        this.productAvatarService = productAvatarService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productImagesService = productImagesService;
        this.availableProductService = availableProductService;
        this.superCategoryService = superCategoryService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }


    public void crawlDataSexType(String link){
        try{

            final Document document = Jsoup.connect(link).get();

            Element getSecond = document.selectFirst("#main-navigation");

            Elements getMenuItem = getSecond.select(".nav-item");

            //Giữ lại
            getMenuItem.remove(4);
            getMenuItem.remove(4);
            getMenuItem.remove(4);
            getMenuItem.remove(getMenuItem.size() - 1);
            getMenuItem.remove(0);

            //Xóa tạm thời
//            getMenuItem.remove(2);
//            getMenuItem.remove(2);
//            getMenuItem.remove(2);
//            getMenuItem.remove(2);
//            getMenuItem.remove(2);
//            getMenuItem.remove(2);
//            getMenuItem.remove(0);


            //Duyệt từng đối tượng trong menu lớn
            for(Element e : getMenuItem){

                String folderURL = "C:\\Users\\Admin\\Desktop\\MySpringProject\\src\\main\\java\\shop-web\\src\\assets\\img";

                //Lấy tên SEX TYPE
                String sexTypeProduct =  e.getElementsByTag("span").first().text();
                sexType = sexTypeProduct;
                sexTypeProduct = sexTypeProduct.replaceAll("[\\/:*?\"<>|]","");;
                //URL folder SEXTYPE
                folderURL = folderURL + "\\" + sexTypeProduct;

                //Tạo folder SEXTYPE
                createFolder(folderURL);

                //Đường link dẫn tới SUPER CATEGORY
                String superCategoryLink = webURL + e.selectFirst("a[href]").attr("href");

                //Tạo đường link dẫn tới SUPER CATEGORY
                if(sexTypeProduct.equalsIgnoreCase("men") || sexTypeProduct.equalsIgnoreCase("women")){
                    superCategoryLink = superCategoryLink + "/clothes";
                }else if(sexTypeProduct.equalsIgnoreCase("kids")){
                    superCategoryLink = superCategoryLink + "/girls";
                }else{
                    superCategoryLink = superCategoryLink + "/living";
                }
                System.out.println("************************************************************************************************************************************");
                System.out.println("************************************************************************************************************************************");
                System.out.println("*************************************" + "START ACCESSING " + sexTypeProduct + " PAGE: " + superCategoryLink + "**************************************");
                System.out.println("************************************************************************************************************************************");
                System.out.println("************************************************************************************************************************************");

                crawlDataSuperCategory(superCategoryLink,folderURL);

                System.out.println("************************************************************************************************************************************");
                System.out.println("************************************************************************************************************************************");
                System.out.println("*************************************" + "END ACCESSING " + sexTypeProduct + " PAGE: " + superCategoryLink + "**************************************");
                System.out.println("************************************************************************************************************************************");
                System.out.println("************************************************************************************************************************************");
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

            }

        }catch (NullPointerException | IOException e){
            e.printStackTrace();
        }
    }



    private void crawlDataSuperCategory(String link, String folder){
        try{
            Document document = Jsoup.connect(link).get();

            //Menu toàn bộ SUPER CATEGORY của thẻ ul
            Element menuSuperCategory = document.selectFirst(".sidenavigation__level-1");

            //Lấy tất cả SUPER CATEGORY ủa ul > li
            Elements superCategories = menuSuperCategory.select(".sidenavigation__level-1 > li");

            for (Element cate : superCategories){

                //SUPER CATEGORY NAME lưu vào database
                String superCategoryDB = cate.getElementsByTag("a").first().text();

                superCategoryDB = superCategoryDB.replaceAll("[\\/:*?\"<>|]","");

                //Lưu Database
                SuperCategoryEntity superCategoryEntity = new SuperCategoryEntity();
                superCategoryEntity.setName(superCategoryDB);

                superCategoryService.createNewSuperCategory(superCategoryEntity);

                //URL SUPER CATEGORY
                String folderSuperCategoryURL = folder + "\\" + superCategoryDB;

                //Tạo folder SUPER CATEGORY
                createFolder(folderSuperCategoryURL);

                //Đường link cơ bản dẫn tới CATEGORY
                String categoryLink = cate.getElementsByTag("a").attr("href");

                if(categoryNames.contains(superCategoryDB))
                    continue;

                System.out.println();
                System.out.println("******************************************************************************************************************************");
                System.out.println("*************************************START ACCESSING SUPER CATEGORY: " + superCategoryDB + "**************************************");
                System.out.println("******************************************************************************************************************************");

                //Tạo đường link tới CATEGORY
                String fullLinkToCategory = webURL + categoryLink;
                System.out.println("LINK SUPER CATEGORY: " + fullLinkToCategory);
                crawlDataCategory(fullLinkToCategory,folderSuperCategoryURL);

                System.out.println("******************************************************************************************************************************");
                System.out.println("*************************************END ACCESSING SUPER CATEGORY: " + superCategoryDB + "**************************************");
                System.out.println("******************************************************************************************************************************");
            }

        }catch (NullPointerException | IOException e){
            e.printStackTrace();
        }
    }



    private void crawlDataCategory(String link, String folder) {
        try{

            Document document = Jsoup.connect(link).get();

            //Menu toàn bộ CATEGORY của thẻ ul
            Element menuCategory = null;

            //Lấy tất cả CATEGORY ủa ul > li
            try{
                menuCategory = document.selectFirst(".sidenavigation__level-2");
            }catch (Exception e){
                e.printStackTrace();
            }
            if(menuCategory == null){
                finalURL = folder;
                System.out.println("**********************************NO CATEGORY*********************************");
                crawlDataProduct(link);
                return;
            }
            Elements categoryLink =menuCategory.select("li");;


            for (Element subCate : categoryLink){

                //CATEGORY NAME lưu vào database
                String categoryDBName = subCate.getElementsByTag("a").first().text();

                categoryDBName = categoryDBName.replaceAll("[\\/:*?\"<>|]","");

                //URL CATEGORY
                String folderCategoryURL = folder + "\\" + categoryDBName;

                //Tạo folder CATEGORY
                createFolder(folderCategoryURL);

                //Đường link cơ bản dẫn tới SUB CATEGORY
                String subCategoryLink = subCate.getElementsByTag("a").attr("href");


                System.out.println();
                System.out.println("*********************************START ACCESSING CATEGORY: " + categoryDBName + "**********************************");

                //Tạo đường link tới SUB CATEGORY
                String fullLinkToSubCategory = webURL + subCategoryLink;
                System.out.println("LINK TO CATEGORY: " + fullLinkToSubCategory);

                crawlDataSubCategory(fullLinkToSubCategory,folderCategoryURL);

                System.out.println("***********************************END ACCESSING CATEGORY: " + categoryDBName + "************************************");

            }


        }catch (NullPointerException | IOException e){
            e.printStackTrace();
        }
    }



    private void crawlDataSubCategory(String link, String folder){
        try {
            Document document = Jsoup.connect(link).get();

            //Menu toàn bộ SUB CATEGORY của thẻ ul
            Element menuSubCategory = null;

            try{
                menuSubCategory = document.selectFirst(".sidenavigation__level-3");
            }catch (Exception e){
                e.printStackTrace();
            }

            //Nếu category không có sub category thì tiến hành lấy thông tinh sản phẩm
            if(menuSubCategory == null){
                finalURL = folder;
                System.out.println("**********************************NO SUB CATEGORY*********************************");
                crawlDataProduct(link);
                return;
            }

            //Lấy tất cả SUB CATEGORY ủa ul > li
            Elements subCategory = menuSubCategory.select("li");

            for (Element subCate : subCategory){

                //SUB CATEGORY NAME lưu vào database
                String subCategoryDBName = subCate.getElementsByTag("a").first().text();

                subCategoryDBName = subCategoryDBName.replaceAll("[\\/:*?\"<>|]","");
                //URL CATEGORY
                String folderSubCategoryURL = folder + "\\" + subCategoryDBName;

                //Tạo folder CATEGORY
                createFolder(folderSubCategoryURL);

                finalURL = folderSubCategoryURL;

                //Đường link cơ bản dẫn tới PRODUCTS
                String productLink = subCate.getElementsByTag("a").attr("href");


                System.out.println();
                System.out.println("*********************************START ACCESSING SUB CATEGORY: " + subCategoryDBName + "**********************************");

                //Tạo đường link tới SUB CATEGORY
                String fullLinkToSubCategory = webURL + productLink;
                System.out.println("LINK TO SUB CATEGORY: " + fullLinkToSubCategory);

                crawlDataProduct(fullLinkToSubCategory);

                System.out.println("***********************************END ACCESSING SUB CATEGORY: " + subCategoryDBName + "************************************");

            }


        }catch (NullPointerException | IOException ignored){
        }
    }

    private void crawlDataProduct(String link) {
        try{

            //Số trang của 1 page
            int pageNumber;
            Document document = Jsoup.connect(link).get();

            Elements pageNumberElements = null;
            try{
                pageNumberElements = document.select(".pagination__item");
            }catch (Exception e){
                e.printStackTrace();
            }
            if(pageNumberElements == null){
                pageNumber = 0;
            }else{
                pageNumber = pageNumberElements.size();
            }

            String page = link + "?pageNumber=";

            if(pageNumber == 0) {
                crawlDataProductInfo(link);
                System.out.println("NO PAGINATION*********************************");
            }else if(pageNumber > 4){
                System.out.println("PAGE NUMBER: " + pageNumber);
                //Duyệt từng page của 1 trang product
                for(int i = 1; i < 4; i++){
                    page = page + i;
                    System.out.println("PAGE: " + page);

                    crawlDataProductInfo(page);

                    page = page.substring(0,page.length() - String.valueOf(i).length());
                }
            }else{
                System.out.println("PAGE NUMBER: " + pageNumber);
                //Duyệt từng page của 1 trang product
                for(int i = 1; i <= pageNumber; i++){
                    page = page + i;
                    System.out.println("PAGE: " + page);

                    crawlDataProductInfo(page);


                    page = page.substring(0,page.length() - String.valueOf(i).length());
                }
            }



        }catch (Exception ignored){}
    }

    //Lấy Thông tin sản phẩm
    private void crawlDataProductInfo(String link) {


        try{
            Document document = Jsoup.connect(link).get();

            Element menuProduct = document.selectFirst(".product-overview__product-list");
            if(menuProduct == null){
                return;
            }

            Elements productItems = menuProduct.children();

            //Duyệt qua từng sản phẩm
            productItems.forEach(element -> {
                Element linkToProductInfo = element.selectFirst(".product-details__style-name");

                String getProductInfoLink = linkToProductInfo.getElementsByTag("a").attr("href");

                String finalLinkToProduct = webURL + getProductInfoLink;

                System.out.println("PRODUCT: " + finalLinkToProduct);
                getProductInfo(finalLinkToProduct);
            });


        }catch (Exception ignored){

        }
    }

    private void getProductInfo(String link) {
        try{
            String productName;
            String productPriceString;
            String productDescription;
            double productPrice;
            List<String> sizeProduct;
            List<String> avatarProduct = new ArrayList<>();
            Document document = Jsoup.connect(link).get();
            Map<String, List<String>> listProductColorImages = new HashMap<>();

            //Thông tin cơ bản của sản phẩm
            productName = document.selectFirst("h1.hc-headline-m").text();
            productPriceString = document.selectFirst("span.spv-price__selling").text();
            productPrice = Double.parseDouble(productPriceString.substring(1));

            //Lấy tất cả màu ảnh
            Elements allProductsColor = document.select(".hc-color-thumb-container li");


            List<String> listProductImages;
            String colorName;
            for(int i = 0; i < allProductsColor.size(); i++){
                if(i == 0){
                    listProductImages = getImagesProduct(link);
                }else{
                    String s = "https:"+ allProductsColor.get(i).getElementsByTag("a").attr("data-style-url");
                    listProductImages = getImagesProduct(s);
                }
                assert listProductImages != null;
                colorName = listProductImages.get(0);
                listProductImages.remove(0);

                listProductColorImages.put(colorName,listProductImages);

            }


            //Element size ảnh
            Element elementSizeProduct = null;
            try{
                elementSizeProduct = document.selectFirst("ul.pullDown");
                if(elementSizeProduct == null){
                    elementSizeProduct = document.selectFirst("ul.sizeButtonList");
                }
            }catch (Exception ignored){}

            //Lấy Size ảnh
            sizeProduct = getSizeProduct(elementSizeProduct);


            //Lấy Element  mô tả sản phẩm
            Element productDescriptionElement = document.selectFirst(".detailInformationContent__infoArea");

            //Mô tả sản phẩm String
            productDescription = getProductDescription(productDescriptionElement);


            System.out.println("PRODUCT NAME: " + productName);
            System.out.println("PRODUCT PRICE: " + productPrice);
            System.out.print("SIZE PRODUCT: ");
            sizeProduct.forEach(x-> System.out.print(x + ", "));
            System.out.println();
            System.out.println("PRODUCT DESCRIPTION: " + productDescription);
            AtomicInteger i = new AtomicInteger();
            for (Map.Entry<String, List<String>> entry : listProductColorImages.entrySet()) {
                System.out.println("COLOR: " + entry.getKey());
                System.out.println("PRODUCT IMAGES: ");
                entry.getValue().forEach(x->{
                    i.getAndIncrement();
                    x = "https:" + x;
                    downloadImage(x);
                    if(String.valueOf(i).equals("2") || String.valueOf(i).equals("1")){
                        avatarProduct.add(x);
                    }
                    System.out.println(x);
                });
                i.set(0);
            }

            System.out.println("avatar: ");
            avatarProduct.forEach(System.out::println);
            System.out.println("****************************************************************************************");
        }catch (Exception ignored){

        }
    }

    private List<String> getImagesProduct(String link){
        try{
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

        }catch (Exception e){
            return null;
        }
    }

    private List<String> getSizeProduct(Element element){
        List<String> res = new ArrayList<>();
        try{
            Elements listSizeProduct = element.getElementsByTag("li");
            listSizeProduct.forEach(e->{
                String size = "";
                if(checkExistElement(e,".sizeButtonList__itemSoldOutButtonSize")){
                    size = e.selectFirst(".sizeButtonList__itemSoldOutButtonSize").text();
                }else if(checkExistElement(e,".pullDown__itemSoldOutButtonSize")){
                    size = e.selectFirst(".pullDown__itemSoldOutButtonSize").text();
                }else{
                    size = e.getElementsByTag("span").first().text();
                }
                res.add(size);
            });
        }catch (Exception e){
            if(res.isEmpty()){
                res.add("ONE SIZE");
            }
        }

        return res;


    }

    private String getProductDescription(Element element){
        try{
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
        }catch (Exception e){
            return "";
        }

    }

    private boolean checkExistElement(Element element, String clazz){
        try{
            String res = element.selectFirst(clazz).html();
            return (res != null);
        }catch (Exception e){
            return false;
        }
    }


    private void downloadImage(String strImageURL){

        //get file name from image path
        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1,strImageURL.lastIndexOf("?") ) + ".jfif";

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
        System.out.println(finalURL);

        try {

            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream( finalURL + "\\" + strImageName );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();

            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void createFolder(String path){
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdirs();
        if(bool){
            System.out.println("Directory " + path + ": created successfully");
        }else{
            System.out.println("Couldnt create directory: " + path);
        }
    }



}
