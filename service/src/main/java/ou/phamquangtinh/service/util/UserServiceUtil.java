package ou.phamquangtinh.service.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceUtil {

    //Chuyển UserEntity thành object
    public <T> T mapUserEntityToObject(UserEntity userEntity, T ok) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userEntity, (Type) ok.getClass());
    }


    //Chuyển Object thành User Entity
    public UserEntity mapObjectToUserEntity(Object objectMapping) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(objectMapping, UserEntity.class);
    }

    //Chuyển UserEntity về UserEntityResponse
    public UserEntityResponse returnUserEntityResponse(UserEntity userEntity){
        List<String> rolesResponse = userEntity.getRoles()
                .stream()
                .map(RoleEntity::getCode)
                .collect(Collectors.toList());

        UserEntityResponse userRes = new UserEntityResponse();
        userRes = mapUserEntityToObject(userEntity, userRes);
        userRes.setRolesResponse(rolesResponse);
        return userRes;
    }


}
