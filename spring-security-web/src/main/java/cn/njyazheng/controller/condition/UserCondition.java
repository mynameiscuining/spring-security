package cn.njyazheng.controller.condition;

import io.swagger.annotations.ApiModelProperty;

public class UserCondition {
    @ApiModelProperty("用户名")//封装到对象的属性字段描述--------->swagger2
    private String username;
    @ApiModelProperty("年龄")//字段描述----------->swagger2
    private Integer age;
    @ApiModelProperty("地址")//字段描述----------->swagger2
    private String address;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
