package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.service.component_service.ISizeService;

@RestController
@RequestMapping("/api/size")
public class SizeController {

    @Autowired
    private ISizeService sizeService;


    @GetMapping("/get/pro_id")
    private ResponseEntity<Object> getSizeByProductId(@RequestParam("pro_id") Long proId){
        return ResponseEntity.ok(sizeService.findSizeByProductId(proId));
    }
}
