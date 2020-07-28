package ou.phamquangtinh.controller.user_controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.controller.security.service.MyUserDetailService;
import ou.phamquangtinh.controller.security.util.JwtUtil;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UsernameAndPasswordAuthenticationRequest;
import ou.phamquangtinh.dto.response.user_response.RegisterResponse;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.service.component_service.IUserService;

@RestController
@Slf4j
@RequestMapping("/api/user/post")
public class PostUser {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private IUserService userService;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/postmapping")
    private String returnString(){
        return "ok";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterReq user) throws Exception {
        UserEntity createUser = userService.createUser(user);
        RegisterResponse res = null;
        if (createUser != null) {
            res = new RegisterResponse(createUser.getUsername(), createUser.getPassword());
        } else {
            throw new Exception("Can't create this user");
        }
        return ResponseEntity.ok(res);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsernameAndPasswordAuthenticationRequest req) throws Exception{
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
}
