package ou.phamquangtinh.controller.security.filter;

import com.google.common.base.Strings;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import ou.phamquangtinh.controller.security.config.JwtConfig;
import ou.phamquangtinh.controller.security.util.JwtUtil;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class JwtTokenVerifier extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private JwtUtil jwtUtil;

    @Qualifier("myUserDetailService")
    @Autowired
    private UserDetailsService userDetailsServie;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Lấy token từ header request
        String authorizationHeader = request.getHeader("Authorization");

        //Kiểm tra có tồn tại token hoặc token có bắt đầu bằng Bearer ?
        if (Strings.isNullOrEmpty(authorizationHeader) || authorizationHeader.contains("Bearer input_your_token")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Lấy đoạn mã token sau Bearer
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        try {
            String username = jwtUtil.extractUsername(token);
            //Kiểm tra nếu tồn tại username và kiểm tra nếu đăng nhập lần đầu
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsServie.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {

                    List<Map<String, String>> authorities = (List<Map<String, String>>) jwtUtil.extractAllClaims(token).get("authorities");

                    List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                            .map(autho -> new SimpleGrantedAuthority(autho.get("authority")))
                            .collect(Collectors.toList());

                    Authentication authentication = new UsernamePasswordAuthenticationToken(username,
                            null, simpleGrantedAuthorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }

        }catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);

    }
}
