package tjjh.foodplatform.user.repository;

import org.springframework.data.repository.CrudRepository;
import tjjh.foodplatform.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);

}
