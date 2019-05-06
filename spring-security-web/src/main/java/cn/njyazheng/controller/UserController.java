package cn.njyazheng.controller;

import cn.njyazheng.controller.condition.UserCondition;
import cn.njyazheng.domain.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @GetMapping
    @JsonView(User.ListUeser.class)
    public List<User>getUsers(UserCondition userCondition){
        LOGGER.info(userCondition.getAddress());
        List<User>userList=new ArrayList<>();
        userList.add(new User("a1","b1",1));
        userList.add(new User("a2","b2",2));
        return userList;
    }
    @GetMapping("/{username}")
    @JsonView(User.UserDetail.class)
    public User getUser(@PathVariable("username")String username){
        LOGGER.info(username);
        User user=new User();
        user.setUsername(username);
        user.setSex(1);
        user.setPassword("b1");
        return user;
    }
    @PostMapping
    public User create(@RequestBody @Valid @Validated(value = {User.Add.class})User user
//                       , BindingResult error
    ){
        //加入BindingResult，可进入方法,不加不能进入方法
//        if(error.hasErrors()){
//            error.getAllErrors().stream().forEach(objectError -> {
//                LOGGER.error(((FieldError)objectError).getField()+":"+objectError.getDefaultMessage());
//            });
//        }
        user.setId(1);
        return user;
    }
    
    @PutMapping("/{id}")
    public User modify(@PathVariable String id ,@RequestBody @Valid User user ){
        user.setUsername("2342342");
        return user;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id ){
       LOGGER.info(id);
    }
}
