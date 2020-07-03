package ou.phamquangtinh.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ou.phamquangtinh.controller.security.models.UsernameAndPasswordAuthenticationRequest;
import ou.phamquangtinh.controller.security.service.MyUserDetailService;
import ou.phamquangtinh.controller.security.util.JwtUtil;
import ou.phamquangtinh.entity.user.User;
import ou.phamquangtinh.service.component_service.user.IUserService;

@RestController
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
    public ResponseEntity<?> register(@RequestBody User user){
        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
        User createUser = userService.createUser(user);
        return ResponseEntity.ok(createUser);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsernameAndPasswordAuthenticationRequest req) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),
                    req.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Token",jwt)
                .body("Login success");

    }

}
