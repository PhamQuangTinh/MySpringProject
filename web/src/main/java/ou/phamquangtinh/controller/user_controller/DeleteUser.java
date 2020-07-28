package ou.phamquangtinh.controller.user_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.service.component_service.IUserService;

@RestController
@RequestMapping(value = "/api/user/delete")
public class DeleteUser {

    @Autowired
    private IUserService userService;

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteUserByID(id);
    }

    @DeleteMapping("/many_users")
    public void deleteManyUsers(@RequestBody Long[] ids) {
        for (Long id : ids) {
            userService.deleteUserByID(id);
        }
    }

}
