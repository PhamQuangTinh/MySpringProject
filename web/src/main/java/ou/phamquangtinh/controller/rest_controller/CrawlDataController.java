package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.service.implement.CrawlDataService;

@RequestMapping("/api/role/")
@RestController
public class CrawlDataController {

    @Autowired
    private CrawlDataService crawlDataService;


//    @PostMapping("post/data-crawling")
//    private String crawlDataController(){
//        crawlDataService.crawlDataSuperCategory();
//
//        return "Success";
//    }
}
