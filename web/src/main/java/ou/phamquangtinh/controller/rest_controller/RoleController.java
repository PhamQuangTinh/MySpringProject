package ou.phamquangtinh.controller.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.dto.request.role_request.CreateRoleRequest;
import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.service.component_service.IRoleService;

@RestController
@RequestMapping("/api/role/")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("get/code/{codeName}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<Object> findRoleById(@PathVariable("codeName") String code) {
        GetUserFromRoleResponse res = roleService.findUserByCodeOfRole(code);
        return ResponseEntity.ok(res);

    }




    //*******************************************POST ROLE*************************************************
    @PostMapping("post/new-role")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<RoleEntity> createnewRole(@RequestBody CreateRoleRequest req){
        RoleEntity res = roleService.createNewRole(req);

        return ResponseEntity.ok(res);
    }
}
