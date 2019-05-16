# spring-security
学习笔记:spring-security  

注解含义
====
@PageDefault  
指定分页参数的默认值   
@JsonView  
根据场景不同的场景同一对象，设置返回属性是否显示  
  1.使用方法(示例User对象)    
     使用接口声明多个视图  
     在值对象get方法上指定视图  
     在controller类的方法上指定视图  
  2.注意事项  
    (1)如果对象创建了视图并且在controller使用了视图，满足这两个条件，对象  
    属性没有注解对应视图和没有视图的属性,结果字段属性都不会显示  
@JsonIgnore  
注解的对象属性不会再json中出现   

**hibernate validator**  
@AssertTrue	  用于boolean字段，该字段只能为true    
@AssertFalse	该字段的值只能为false  
@CreditCardNumber	对信用卡号进行一个大致的验证  
@DecimalMax	只能小于或等于该值  
@DecimalMin	只能大于或等于该值  
@Digits(integer=,fraction=)	检查是否是一种数字的整数、分数,小数位数的数字  
@Email	检查是否是一个有效的email地址  
@Future	检查该字段的日期是否是属于将来的日期  
@Length(min=,max=)	检查所属的字段的长度是否在min和max之间,只能用于字符串  
@Max	该字段的值只能小于或等于该值  
@Min	该字段的值只能大于或等于该值  
@NotNull	不能为null  
@NotBlank	不能为空，检查时会将空格忽略  
@NotEmpty	不能为空，这里的空是指空字符串  
@Null	检查该字段为空  
@Past	检查该字段的日期是在过去  
@Pattern(regex=,flag=)	被注释的元素必须符合指定的正则表达式  
@Range(min=,max=,message=)	被注释的元素必须在合适的范围内  
@Size(min=, max=)	检查该字段的size是否在min和max之间，可以是字符串、数组、集合、Map等  
@URL(protocol=,host,port)	检查是否是一个有效的URL，如果提供了protocol，host等，则该URL还需满足提供的条件  
@Valid	该注解主要用于字段为一个包含其他对象的集合或map或数组的字段，或该字段直接为一个其他对象的引用，  
这样在检查当前对象的同时也会检查该字段所引用的对象   

@Validated 可以配合hibernate validator的group使用,设置哪些方法确定是否要验证hibernate validator的注解   
 注意  
 (1)使用@Validated 没有设置group和非对应group的hibernate validator将不做校验  
 (2)@Validated和@Valid同时使用,谁在前谁起作用   
 
异步处理rest服务
===
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/rest-async.jpg)

**spring-security核心功能:**  
认证  
授权  
攻击防护  
基本原理:过滤器链  
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/security-principle.jpg)

自定义认证逻辑  
1.处理用户信息获取逻辑接口   UserDetailsService  
2.处理用户校验逻辑接口  UserDetails  
3.密码加密接口 PasswordEncoder  





    

