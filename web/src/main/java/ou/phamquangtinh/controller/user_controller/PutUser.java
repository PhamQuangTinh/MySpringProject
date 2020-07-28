package ou.phamquangtinh.controller.user_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.service.component_service.IUserService;

@RestController
@RequestMapping("/api/user/put")
public class PutUser {

    @Autowired
    IUserService userService;

    @PutMapping("/updation")
    public ResponseEntity<Object> updateUser(UpdateUserReq user) {

        UserEntity userOK = userService.updateUser(user);

        return ResponseEntity.ok(userOK);
    }


}
