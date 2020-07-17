package ou.phamquangtinh.controller.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ou.phamquangtinh.dto.request.user_request.UsernameAndPasswordAuthenticationRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter_ChuThich extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtUsernameAndPasswordAuthenticationFilter_ChuThich(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);

            return authentication;

        } catch (IOException e) {
            throw new RuntimeException("Incorrect username and password",e);
        }
    }


    //Nếu authentication thành công
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String secretKey = "DoAnTotNghiepPhamQuangTinh1654012103VaDayLaSecretKeyCuaJWTSpringSecurity";
        String token = Jwts.builder()
                            .setSubject(authResult.getName())
                            .claim("authorities",authResult.getAuthorities())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                            .compact();
        response.addHeader("Authorization","Bearer " + token);

    }
}
