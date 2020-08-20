package ou.phamquangtinh.controller.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.service.component_service.IUserService;

@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username);
        return new MyUserDetails(user);
    }
}
