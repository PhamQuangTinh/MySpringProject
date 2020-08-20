package ou.phamquangtinh.service.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.PageMetadata;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.UserEntity;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonUtil {

    //Chuyển UserEntity thành object
    public <T extends Object> T mapUserEntityToObject(UserEntity userEntity, T ok) {
        ModelMapper mapper = new ModelMapper();
        T objectMapping = mapper.map(userEntity, (Type) ok.getClass());
        return objectMapping;
    }


    //Chuyển Object thành User Entity
    public UserEntity mapObjectToUserEntity(Object objectMapping) {
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntityRes = mapper.map(objectMapping, UserEntity.class);
        return userEntityRes;
    }

    //Chuyển UserEntity về UserEntityResponse
    public <T extends Object> T returnUserEntityResponse(T entity){
        Object objectRes = null;
        if(entity instanceof UserEntity){
            List<String> rolesResponse = ((UserEntity)entity).getRoles()
                    .stream()
                    .map(roleEntity -> roleEntity.getCode())
                    .collect(Collectors.toList());

            UserEntityResponse userRes = new UserEntityResponse();
            userRes = mapUserEntityToObject((UserEntity) entity, userRes);
            userRes.setRolesResponse(rolesResponse);

            objectRes = userRes;
        }

        return (T)objectRes;
    }

    public <T> ListResponsePagination getListResponsePagination(Page<T> pageUserEntity){

        ListResponsePagination listUsers = new ListResponsePagination<>();

        PageMetadata pageMetadata = new PageMetadata();

        pageMetadata.setPage(pageUserEntity.getNumber() + 1);
        pageMetadata.setSize(pageUserEntity.getSize());
        pageMetadata.setTotalElements(pageUserEntity.getTotalElements());
        pageMetadata.setTotalPages(pageUserEntity.getTotalPages());

        listUsers.setPageMetadata(pageMetadata);


        listUsers.setListResponse(pageUserEntity.getContent());


        return listUsers;

    }


}
