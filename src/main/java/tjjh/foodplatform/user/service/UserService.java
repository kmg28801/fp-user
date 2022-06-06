package tjjh.foodplatform.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tjjh.foodplatform.user.dto.UserDto;
import tjjh.foodplatform.user.entity.UserEntity;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String email);
}
