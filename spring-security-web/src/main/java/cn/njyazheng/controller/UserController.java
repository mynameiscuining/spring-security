package cn.njyazheng.controller;

import cn.njyazheng.controller.condition.UserCondition;
import cn.njyazheng.domain.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}