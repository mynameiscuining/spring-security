package cn.njyazheng.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//自定义用户账号密码认证
@Component
public class CustomUserDetailsService  implements UserDetailsService {
    private static final Logger logger= LoggerFactory.getLogger(CustomUserDetailsService.class);
    
    @Autowired
    private  PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("-------------------------------------username:"+username);
        
        //根据用户名查找用户信息
        
        //根据用户信息,做一定的逻辑判断,并选择合适的User的构造函数,以下方式是:是否用户删除,是否用户过期,是否用户密码过期,是否用户锁定
        //public User(String username, String password, boolean enabled,
        //			boolean accountNonExpired, boolean credentialsNonExpired,
        //			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        
        
        //第一个参数用户(认证),
        // 第二个参数是数据库读出的密码(认证),spring security5.0,之后这个密码需要一个加密PasswordEncoder,方式见BrowserConfiguration类
        // ,第三个参数是权限集合(授权)
        //这里的User是spring security提供的UserDetails的实现
        //这里的构造函数,只是一个示例
        String encodpassword=passwordEncoder.encode("123456");
        logger.info("-------------------------------------加密的password"+encodpassword);
        return new User(username,encodpassword, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,root"));
    }
}
