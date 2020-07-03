package ou.phamquangtinh.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityListeners;

@SpringBootApplication
@PropertySource(value = {"classpath:domain.properties","classpath:application.properties"})
@EnableJpaRepositories(basePackages = {"ou.phamquangtinh.repository"})
@EntityScan(basePackages = {"ou.phamquangtinh.entity"})
@EnableJpaAuditing
@ConfigurationPropertiesScan("ou.phamquangtinh")
@ComponentScan(basePackages = {"ou.phamquangtinh"})
public class Application{
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }


//    @Autowired
//    private UserDetails userDetails;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        System.out.println("user");
//    }




}