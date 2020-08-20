package ou.phamquangtinh.service.crawl_data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.repository.ProductJPARepository;

import java.io.*;
import java.net.URL;
import java.util.*;


public class crawl {

    @Autowired
    private ProductJPARepository productJPARepository;


//    @Autowired
//    private SizeJPARepository sizeJPARepository;

//    @Autowired
//    private ProductSizeJPARepository productSizeJPARepository;

//    @Autowired
//    private CategoryJPARepository cateJPARepository;

    private String productName = "";
    private double productPrice = 0.0;
    private String productDescription = "";
    private String category = "";

    //Tìm Kiếm categorty. coppy từ CategoryService
    public CategoryEntity findByCategoryName(String categoryName) {
//        Optional<CategoryEntity> cate = cateJPARepository.findByCategoryName(categoryName);
//        cate.orElseThrow(()->new RuntimeException("can't find category: " + categoryName));
//        return cate.get();
        return null;
    }


    //Tiến hành cào dữ liệu
    public void crawl(){
        String[] links = {"ao-khoac","ao-hoodie","ao-so-mi","ao-thun","quan-dai","quan-dai-ma-bu","quan-jean-on-gian"
                ,"quan-jogger","quan-kaki-dai","quan-tay","quan-short","giay-nam","dep","bltx","balo-laptop"
                ,"tui-eo-cheo","phu-kien-nam","vi-da","non"};

        //Duyệt từng phần tử trong links. để tạo thành link kết nối đối với mỗi phần tử
        for(int i = 0; i < links.length; i++){
            category = links[i];
            System.out.println(category);
            String url = "https://yame.vn/shop/" + category + "?page=";
            String file = new File("").getAbsolutePath();
            //thư mục lưu ảnh
            String saveFolderURL = file + "\\src\\main\\java\\shop-web\\src\\assets\\img\\man-product\\" + category;

            //Cho duyệt qua 4 trang đối với mỗi đường link
            for(int j = 0; j < 4; j++){

                url = url + String.valueOf(j+1);

                System.out.println(url);

                try{
                    //Kết nối web
                    final Document document = Jsoup.connect(url).get();

                    //Tìm các elements có class là "row"
                    Elements element = document.getElementsByClass("row");

                    //Duyệt qua từng element
                    element.forEach(
                            ele1 ->{

                                final List<String> images = new ArrayList<>();

                                try{
                                    Elements layAnh = ele1.select(".pitem");

                                    layAnh.forEach(
                                            la ->{
                                                Elements anhs = la.getElementsByTag("img");
                                                anhs.forEach(
                                                        ele2 ->{
                                                            String ok = "D:\\Users\\pc\\WorkWithJava\\my-spring-boot-project\\src\\main\\java\\shop-web\\src";
                                                            String text2 = ele2.attr("src");
//                                                            String catAnh = downloadImage(saveFolderURL,text2);
//                                                            catAnh = catAnh.replace(ok,"");
//                                                            images.add(catAnh);
                                                        }
                                                );


                                                //Lấy thẻ a nơi chứa địa chỉ tới chi tiết sản phẩm
                                                Element ok = la.getElementsByTag("a").first();

                                                String text1 = "";
                                                try{
                                                    text1 = ok.attr("href");
                                                }catch (NullPointerException e){
                                                }
                                                //Tạo địa chỉ từng sản phẩm để truy câp vào lấy thông tin tên, mô tả sản phẩm
                                                text1 = "http://yame.vn" + text1;

                                                try {
                                                    Element elen1 = null;
                                                    if(text1.length() > 50){
                                                        //Truy cập vào thông tin sản phẩm
                                                        Document document1 = Jsoup.connect(text1).get();

                                                        elen1 = document1.selectFirst(".col-md-8 > .ditem");
                                                    }

                                                    try{
                                                        //chi tiết sản phẩm
                                                        Elements elen2 = elen1.getElementsByTag("h5");

                                                        productName = elen2.get(0).html();

                                                        String gia = elen2.get(1).text();

                                                        gia = gia.replace("đ ", "");

                                                        gia = gia.replace(",","");

                                                        productPrice = Double.parseDouble(gia);

                                                        Elements elen3 = elen1.select(".col-md-4");

                                                        StringBuilder sb = new StringBuilder();

                                                        elen3.forEach(ee -> sb.append(ee.text()));

                                                        productDescription = sb.toString();

                                                    }catch (NullPointerException e){}


                                                } catch (IOException e) { }


                                                ProductImfo imfo = new ProductImfo(productName,productDescription,"nam",productPrice,images);
//                                                saveImage(saveFolderURL, category,imfo);

                                                images.clear();
                                                System.out.println("***********************************************************************");

                                            }
                                    );


                                }catch (NullPointerException e){}

                            }

                    );


                }catch (Exception e){
                    e.printStackTrace();
                }

                url = url.substring(0,url.length()-1);
            }

        }
    }

//    private void saveImage(String folder,String category, ProductImfo productImfo){

//        Optional<ProductEntity> productEntityFirst = productJPARepository.findByProductName(productName);
//
//        if(!productEntityFirst.isPresent()){
//
//            ModelMapper modelMapper = new ModelMapper();
//
//            ProductEntity productEntity = modelMapper.map(productImfo,ProductEntity.class);
//
//            CategoryEntity cate = findByCategoryName(category);
//
//
//
//            Collection<CategoryEntity> ca = new ArrayList<>();
//
//            Collection<ProductImagesEntity> pi = new HashSet<>();
//

//            ca.add(cate);
//            productEntity.setCategorys(ca);


//            ProductEntity res2 = productJPARepository.saveAndFlush(productEntity);

            //Tạo danh sách product Images và lưu vào database product_images
//            productImfo.getImages().forEach(x ->{
//                ProductImagesEntity pro = new ProductImagesEntity(null,x,res2);
//                proImagesJpaRepository.saveAndFlush(pro);
//                pi.add(pro);
//            });
//
//            //Lưu vào product
//            res2.setProductImagesEntities(pi);

//            if(category.startsWith("ao") || category.startsWith("quan")){
//                SizeEntity sizeEntity_S = sizeJPARepository.findBySizeType("S");
//                SizeEntity sizeEntity_M = sizeJPARepository.findBySizeType("M");
//                SizeEntity sizeEntity_L = sizeJPARepository.findBySizeType("L");
//                SizeEntity sizeEntity_XL = sizeJPARepository.findBySizeType("XL");

//                AvailableProducts productSizeEntity_S =
//                        new AvailableProducts(new ProductSizeKey(res2.getId(),sizeEntity_S.getId()),10,res2,sizeEntity_S);
//                productSizeJPARepository.saveAndFlush(productSizeEntity_S);
//
//                AvailableProducts productSizeEntity_M =
//                        new AvailableProducts(new ProductSizeKey(res2.getId(),sizeEntity_M.getId()),10,res2,sizeEntity_M);
//                productSizeJPARepository.saveAndFlush(productSizeEntity_M);
//
//                AvailableProducts productSizeEntity_L =
//                        new AvailableProducts(new ProductSizeKey(res2.getId(),sizeEntity_L.getId()),10,res2,sizeEntity_L);
//                productSizeJPARepository.saveAndFlush(productSizeEntity_L);
//
//                AvailableProducts productSizeEntity_XL =
//                        new AvailableProducts(new ProductSizeKey(res2.getId(),sizeEntity_XL.getId()),10,res2,sizeEntity_XL);
//                productSizeJPARepository.saveAndFlush(productSizeEntity_XL);
//
//                Collection<AvailableProducts> productSizeEntities = new HashSet<>();
//                productSizeEntities.add(productSizeEntity_S);
//                sizeEntity_S.setProductSizeEntities(productSizeEntities);
//                productSizeEntities.add(productSizeEntity_M);
//                sizeEntity_M.setProductSizeEntities(productSizeEntities);
//                productSizeEntities.add(productSizeEntity_L);
//                sizeEntity_L.setProductSizeEntities(productSizeEntities);
//                productSizeEntities.add(productSizeEntity_XL);
//                sizeEntity_XL.setProductSizeEntities(productSizeEntities);
//
//                res2.setProductSizeEntities(productSizeEntities);
//
//            }
//
//
//            System.out.println("Create success");
//
//        }
//
//        else{
////            CategoryEntity cate = findByCategoryName(category);
////            ProductEntity productUpdate = productJPARepository.getOne(productEntityFirst.get().getId());
////            productUpdate.getCategorys().add(cate);
////
////            productJPARepository.saveAndFlush(productUpdate);
////            System.out.println("Update success");
//        }
//
//
//
//    }
//
//    private String downloadImage(String folder, String strImageURL){
//
//        //get file name from image path
//        String strImageName =
//                strImageURL.substring( strImageURL.lastIndexOf("/") + 1,strImageURL.lastIndexOf("?"));
//
//        String saveImage = strImageURL.substring(strImageURL.lastIndexOf("?") + 1,strImageURL.lastIndexOf("&"));
//
//        strImageName = folder + "\\" + category + "_" + saveImage + "_" + strImageName;
//
//        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
//
//        try {
//
//            //open the stream from URL
//            URL urlImage = new URL(strImageURL);
//            InputStream in = urlImage.openStream();
//
//            byte[] buffer = new byte[4096];
//            int n = -1;
//
//            OutputStream os =
//                    new FileOutputStream(strImageName );
//
//            //write bytes to the output stream
//            while ( (n = in.read(buffer)) != -1 ){
//                os.write(buffer, 0, n);
//            }
//
//            //close the stream
//            os.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return strImageName;
//
//
//    }


}
