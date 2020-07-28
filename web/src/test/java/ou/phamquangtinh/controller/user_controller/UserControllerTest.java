package ou.phamquangtinh.controller.user_controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    protected MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    protected void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
