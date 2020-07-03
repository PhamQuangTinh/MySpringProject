package ou.phamquangtinh.controller.user;

import org.springframework.web.bind.annotation.*;
import ou.phamquangtinh.entity.user.User;

@RestController
public class GetUser {

    @RequestMapping("/hello")
    public @ResponseBody String firstPage() {
        System.out.println("checked at hello");
        return "<h1>hello world</h1>";
    }

    @RequestMapping("/")
    public String firstPag() {
        System.out.println("checked at nothing");
        return "<h1>ok</h1>";
    }

    @GetMapping("/api")
    public @ResponseBody String ok() {
        System.out.println("Checked at API");
        return "api check";
    }



//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//
//        final UserDetails userDetails = myUserDetailService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }



}
