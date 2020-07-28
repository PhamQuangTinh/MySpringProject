package ou.phamquangtinh.controller.user_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.service.component_service.IUserService;

@RestController
@RequestMapping("/api/user/get")
public class GetUser {

    @Autowired
    private IUserService userService;

    @GetMapping("/user_name/{username}")
    public ResponseEntity<Object> getUserByID(@PathVariable("username") String userName) {

        UserEntityResponse res = userService.findByUserNameResponse(userName);

        return ResponseEntity.ok(res);
    }





}
