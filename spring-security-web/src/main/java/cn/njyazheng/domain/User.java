package cn.njyazheng.domain;

import com.fasterxml.jackson.annotation.JsonView;

public class User {
    public interface ListUeser{};//设置用户数组的视图
    public interface UserDetail extends ListUeser{};//设置用户详细的视图并继承用户的数组视图
    
    private String username;
    private String password;
    private Integer sex;
    public User(){}
    public User(String username, String password, Integer sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }
    
    public Integer getSex() {
        return sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    @JsonView(ListUeser.class)
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    @JsonView(UserDetail.class)
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
