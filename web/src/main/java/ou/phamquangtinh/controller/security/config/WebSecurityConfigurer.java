package ou.phamquangtinh.controller.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ou.phamquangtinh.controller.security.filter.JwtTokenVerifier;

import javax.servlet.Filter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //sử dụng @PreAuthorize() trong controller
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenVerifier jwtTokenVerifier;

    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/",
                        "/api/user/post/hello",
                        "/api/user/post/register",
                        "/api/user/post/login",
                        "/api/user/put/updation",
                        "/api/user/get/user_name/{username}",
                        "/api/user/get/id/{id}",
                        "/api/user/delete/id/{id}",
                        "/api/user/delete/many_users",

                        //Product
                        "/api/product/get/all_product",
                        "/api/product/get/id/{id}",
                        "/api/product/get/cate_id/{id}",
                        "/api/product/get/cate_name/{name}",
                        "/api/product/get/sub_cate_id/{id}",
                        "/api/product/get/sub_cate_name/{name}",
                        "/api/product/get/color/{id}",
                        "/api/product/get/size/{id}",
                        "/api/product/get/product_name_or_description",
                        "/api/product/get/product_info",
                        "/api/product/get/sex_type_and_price",
                        "/api/product/get/product_name",


                        //Product Color
                        "/api/product_color/get/proId_and_color_id",


                        //Product Images
                        "/api/product_images/get/product_id",
                        "/api/product_images/get/color_id_and_pro_id",

                        //Product Comment
                        "/api/product_comment/get/all-comment",

                        //Color
                        "/api/color/get/pro_id/{id}",


                        //size
                        "/api/size//get/pro_id",

                        //Available Product
                        "/api/available_product/post/cart_checking",
//                        "https://localhost:8080/api/order/paypal/success",
//                        "https://localhost:8080/api/order/paypal/cancel"

                        "/api/role/post/data-crawling"
                )
                .permitAll()
                .antMatchers("/api/user/post/postmapping")
                .hasAnyAuthority("SUPER_ADMIN")
//                .antMatchers("/api/user/put/likination")
//                .hasAnyAuthority("USER")
                .anyRequest()
                .authenticated();


        //                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
