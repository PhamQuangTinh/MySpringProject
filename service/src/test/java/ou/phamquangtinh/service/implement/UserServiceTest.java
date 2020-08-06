package ou.phamquangtinh.service.implement;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }
//    @TestConfiguration
//    public static class UserServiceTestConfiguration{
//
//        @Bean
//        UserService userService(){
//            return new UserService();
//        }
//    }
//
//    @Autowired
//    UserService userService;
//
//    @MockBean
//    UserRepository userRepository;

//    @Before
//    public void setup(){
//        UserEntity user = new UserEntity("1","1","1","1","1","1",new ArrayList<>());
//        Mockito.when(userRepository.findAll())
//                .thenReturn(Stream.of(new UserEntity("string","string","1","1","1","1",new ArrayList<>())
//                        ,new UserEntity("quangtinh","123456","2","2","2","2",new ArrayList<>()))
//                        .collect(Collectors.toList()));
//    }
//    @Test
//    public void createUser() {
//        RegisterReq req = new RegisterReq("quangtinh","123456","2","2","2","2",new ArrayList<>());
//        Assert.assertEquals(new UserEntity("quangtinh","123456","2","2","2","2",new ArrayList<>())
//                ,userService.createUser(req));
//    }


}