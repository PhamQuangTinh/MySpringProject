package ou.phamquangtinh.controller.security.config;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@Getter
@Setter
//inject các biến trong application.properties vào biến trong JwtConfig theo tên
@ConfigurationProperties(prefix = "application.jwt") //set up properties có prefix là application.jwt
public class JwtConfig {
    private String secretkey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;


    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
