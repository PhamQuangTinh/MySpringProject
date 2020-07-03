package ou.phamquangtinh.service.implement.user_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.user.User;
import ou.phamquangtinh.repository.user_repository.UserRepository;
import ou.phamquangtinh.service.component_service.user.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
}
