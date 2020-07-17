package ou.phamquangtinh.controller.user;

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
import ou.phamquangtinh.controller.exception.ApiRequestException;
import ou.phamquangtinh.controller.security.service.MyUserDetailService;
import ou.phamquangtinh.controller.security.util.JwtUtil;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UsernameAndPasswordAuthenticationRequest;
import ou.phamquangtinh.dto.response.CommonResponse;
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
        log.info("da nay gia tri");
        return "ok";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterReq user) {
        UserEntity createUser = userService.createUser(user);
        log.info("Start register");
        RegisterResponse res = null;
        if (createUser != null) {
            log.info("logging success");
            res = new RegisterResponse(createUser.getUsername(), createUser.getPassword());
        } else {
            log.warn("Exit User in Database");
            throw new ApiRequestException("Can't create this user");
        }
        return ResponseEntity.ok(res);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsernameAndPasswordAuthenticationRequest req) throws Exception{
        log.info("Start login");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),
                    req.getPassword()));
        } catch (DisabledException e) {
            log.error("Can't see this user");
            throw new ApiRequestException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            log.warn("Incorrect username and password");
            throw new ApiRequestException("Incorrect username or password");
        }

        log.info("Login success");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        CommonResponse res = new CommonResponse();
        final String jwt = jwtUtil.generateToken(userDetails);
        res.setData(userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Token", jwt)
                .body(res);

    }
}
