package tjjh.foodplatform.user.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tjjh.foodplatform.user.dto.UserDto;
import tjjh.foodplatform.user.entity.UserEntity;
import tjjh.foodplatform.user.service.UserService;
import tjjh.foodplatform.user.vo.RequestUser;
import tjjh.foodplatform.user.vo.ResponseUser;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment environment;


    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", this.environment.getProperty("local.server.port"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        this.userService.createUser(userDto);
        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = this.userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(i -> {
            result.add(new ModelMapper().map(i, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUsers(@PathVariable("userId") String userId) {
        UserDto userDto = this.userService.getUserByUserId(userId);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    

}
