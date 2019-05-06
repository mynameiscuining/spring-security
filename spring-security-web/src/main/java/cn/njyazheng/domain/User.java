package cn.njyazheng.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public interface ListUeser{};//设置用户数组的视图
    public interface UserDetail extends ListUeser{};//设置用户详细的视图并继承用户的数组视图
    
    private  Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    private Integer sex;
    @Past(message = "生日只能是过去的时间")
    private Date birthday;
    public User(){}
    public User(String username, String password, Integer sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
