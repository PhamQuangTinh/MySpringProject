package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.service.component_service.IColorService;

@RestController
@RequestMapping("/api/color")
public class ColorController {

    @Autowired
    private IColorService colorService;

    @GetMapping("/get/pro_id/{id}")
    public ResponseEntity<ListResponsePagination> getColorByProductId(@PathVariable("id") Long id,
                                                                      @RequestParam("page") int page,
                                                                      @RequestParam("size") int size){
        return ResponseEntity.ok(colorService.findColorByProductId(id,page,size));
    }
}
