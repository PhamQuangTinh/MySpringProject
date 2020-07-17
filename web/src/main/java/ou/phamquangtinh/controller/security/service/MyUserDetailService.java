package ou.phamquangtinh.controller.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.user_repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found" + username));
        return user.map(MyUserDetails::new).get();
    }
}
