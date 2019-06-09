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

**自定义认证逻辑**  
1.处理用户信息获取逻辑接口   UserDetailsService  
2.处理用户校验逻辑接口  UserDetails  
3.密码加密接口 PasswordEncoder  

**认证流程**  
1.认证流程处理说明(表单登录为例)  
  登录请求-->UsernamePasswordAuthenticationFilter(获取请求信息)-->AuthenticationManager(管理AuthenticationProvider)-->
  AuthenticationProvider(校验)-->UserDetailsService-->UserDetails->Authentication(已认证)  
2.认证结果如何在多个请求之间共享  
  Authentication(已认证)-->SecurityContext-->SecurityContextHolder-->
  SecurityContextPersistenceFilter(过滤器链的最前面,请求进来检查session是否有SecurityContext,有就放到线程里,
  没有就过,返回是检查线程,有就放在session里)  
3.获取认证的用户信息  
  第一种  Authentication=SecurityContextHolder.getContext().getAuthentication()  
  第二种  Authentication=Controller层直接当做方法参数Authentication  
  第三种 UserDetails=Controller层直接当做方法参数@AuthenticationPrincipal UserDetails  
  
**谷歌验证码**  
<table><tbody><tr><td><strong>Constant</strong></td>
			<td><strong>描述</strong></td>
			<td><strong>默认值</strong></td>
		</tr><tr><td>kaptcha.border</td>
			<td>图片边框，合法值：yes , no</td>
			<td>yes</td>
		</tr><tr><td>kaptcha.border.color</td>
			<td>边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.</td>
			<td>black</td>
		</tr><tr><td>kaptcha.border.thickness</td>
			<td>边框厚度，合法值：&gt;0</td>
			<td>1</td>
		</tr><tr><td>kaptcha.image.width</td>
			<td>图片宽</td>
			<td>200</td>
		</tr><tr><td>kaptcha.image.height</td>
			<td>图片高</td>
			<td>50</td>
		</tr><tr><td>kaptcha.producer.impl</td>
			<td>图片实现类</td>
			<td>com.google.code.kaptcha.impl.DefaultKaptcha</td>
		</tr><tr><td>kaptcha.textproducer.impl</td>
			<td>文本实现类</td>
			<td>com.google.code.kaptcha.text.impl.DefaultTextCreator</td>
		</tr><tr><td>kaptcha.textproducer.char.string</td>
			<td>文本集合，验证码值从此集合中获取</td>
			<td>abcde2345678gfynmnpwx</td>
		</tr><tr><td>kaptcha.textproducer.char.length</td>
			<td>验证码长度</td>
			<td>5</td>
		</tr><tr><td>kaptcha.textproducer.font.names</td>
			<td>字体</td>
			<td>Arial, Courier</td>
		</tr><tr><td>kaptcha.textproducer.font.size</td>
			<td>字体大小</td>
			<td>40px.</td>
		</tr><tr><td>kaptcha.textproducer.font.color</td>
			<td>字体颜色，合法值： r,g,b &nbsp;或者 white,black,blue.</td>
			<td>black</td>
		</tr><tr><td>kaptcha.textproducer.char.space</td>
			<td>文字间隔</td>
			<td>2</td>
		</tr><tr><td>kaptcha.noise.impl</td>
			<td>干扰实现类</td>
			<td>com.google.code.kaptcha.impl.DefaultNoise</td>
		</tr><tr><td>kaptcha.noise.color</td>
			<td>干扰&nbsp;颜色，合法值： r,g,b 或者 white,black,blue.</td>
			<td>black</td>
		</tr><tr><td>kaptcha.obscurificator.impl</td>
			<td>图片样式：&nbsp;<br>
			水纹com.google.code.kaptcha.impl.WaterRipple&nbsp;<br>
			鱼眼com.google.code.kaptcha.impl.FishEyeGimpy<br>
			阴影com.google.code.kaptcha.impl.ShadowGimpy</td>
			<td>com.google.code.kaptcha.impl.WaterRipple</td>
		</tr><tr><td>kaptcha.background.impl</td>
			<td>背景实现类</td>
			<td>com.google.code.kaptcha.impl.DefaultBackground</td>
		</tr><tr><td>kaptcha.background.clear.from</td>
			<td>背景颜色渐变，开始颜色</td>
			<td>light grey</td>
		</tr><tr><td>kaptcha.background.clear.to</td>
			<td>背景颜色渐变，&nbsp;结束颜色</td>
			<td>white</td>
		</tr><tr><td>kaptcha.word.impl</td>
			<td>文字渲染器</td>
			<td>com.google.code.kaptcha.text.impl.DefaultWordRenderer</td>
		</tr><tr><td>kaptcha.session.key</td>
			<td>session key</td>
			<td>KAPTCHA_SESSION_KEY</td>
		</tr><tr><td>kaptcha.session.date</td>
			<td>session date</td>
			<td>KAPTCHA_SESSION_DATE</td>
		</tr></tbody></table>  
		
**REMEMBER_ME**   
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/rememberme.jpg)  

OAuth  
===
四种授权模式:  
1.授权码模式  
2.简化模式  
3.密码模式  
4.客户端模式  

权限
===
**权限流程**  
1.FilterSecurityInterceptor 过滤器链上最后的过滤器,判断经过前面的过滤器链后的请求能否能访问到后面的资源  
也是授权判断流程的主入口   
2.AccessDecisionManager是一个接口,AbstractAccessDecisionManager抽象实现,管理一组AccessDecisionVector  
每个AccessDecisionVector的逻辑是对请求的投票,通过还是不过过,最终过不过还是由AbstractAccessDecisionManager  
的实现确定  
3.AffirmativeBased,ConsensusBased,UnanimousBased是AbstractAccessDecisionManager3个实现逻辑  
AffirmativeBased(Security默认使用)只要有一个AccessDecisionVector通过,就通过  
ConsensusBased 比较AccessDecisionVector的投票个数,由投票数大的确定  
UnanimousBased只要有一个AccessDecisionVector不通过,就会不通过  
4.spring-security3之后,在web环境下,Vector都由一个WebExpressVector处理
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/role.jpg)  
**权限表达式**  
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/express_role.jpg)   
**RBAC(Role-Based Access Controll)**  
![Image text](https://raw.githubusercontent.com/mynameiscuining/spring-security/master/rbac.jpg)  










    

