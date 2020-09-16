package ou.phamquangtinh.controller.rest_controller;


import com.paypal.api.payments.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.controller.security.service.MyUserDetailService;
import ou.phamquangtinh.controller.security.util.JwtUtil;
import ou.phamquangtinh.dto.request.user_request.*;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.user_response.RegisterResponse;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.service.component_service.IPaypalService;
import ou.phamquangtinh.service.component_service.IUserService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;




    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //*******************************************POST USER**************************************************

    @PostMapping("/post/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterReq user) throws Exception {
        UserEntity createUser = userService.createUser(user);
        RegisterResponse res = null;
        if (createUser != null) {
            res = new RegisterResponse(createUser.getUsername(), createUser.getPassword());
        } else {
            throw new Exception("Existed user already");
        }
        return ResponseEntity.ok(res);
    }


    @PostMapping("/post/login")
    public ResponseEntity<?> login(@RequestBody UsernameAndPasswordAuthenticationRequest req) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),
                    req.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }



        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Token", jwt)
                .body(userDetails);


    }


    @PostMapping("/post/likination")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public void userLikeProduct(@RequestBody UserLikeProductReq req) {
        userService.likeProduct(req.getUserId(), req.getProId());
    }

    @PostMapping("/post/unlikination")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public void userUnLikeProduct(@RequestBody UserLikeProductReq req) {
        userService.unLikeProduct(req.getUserId(), req.getProId());
    }

    @PostMapping("/post/comment")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_ADMIN')")
    public void userCommentProduct(@RequestBody UserCommentReq userCommentReq) {

        userService.addNewComment(userCommentReq.getUserId(), userCommentReq.getProductId()
                , userCommentReq.getContent());
    }


    //****************************************GET USER*******************************************
    @GetMapping("/get/user_name/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String userName) {

        UserEntity res = userService.findByUserNameResponse(userName);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {

        UserEntity res = userService.findUserByIdResponse(id);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/list_users/{code}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<Object> getListUsersByRoleCode(@PathVariable("code") String code
            , @RequestParam("page") int page, @RequestParam("size") int size) {
        ListResponsePagination res = userService.findUsersByRoleCodePagination(code, page, size);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/last_name/{lastName}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<Object> getUserByLastName(@PathVariable("lastName") String lastName) {

        List<UserEntity> res = userService.findByLastName(lastName);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/all_user/pagination")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<Object> getAllUsersPagination(@RequestParam("page") int page, @RequestParam("size") int size) {
        ListResponsePagination res = userService.findAllUsers(page, size);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/first_name_or_last_name_constraining")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<Object> getUserByFirstNameOrLastName(@RequestParam("page") int page
            , @RequestParam("size") int size, @RequestParam("keyword") String keyword) {
        ListResponsePagination res = userService.findByLastNameOrFirstNameContaining(keyword, page, size);
        return ResponseEntity.ok(res);
    }


    //**************************************************PUT USER*************************************************
    @PutMapping("/put/updation")
    public ResponseEntity<Object> updateUser(UpdateUserReq user) {

        UserEntity userOK = userService.updateUser(user);

        return ResponseEntity.ok(userOK);
    }


    //************************************************DELETE USER***********************************************
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteUserByID(id);
    }


    @DeleteMapping("/delete/many_users")
    public void deleteManyUsers(@RequestBody Long[] ids) {
        for (Long id : ids) {
            userService.deleteUserByID(id);
        }
    }


}
