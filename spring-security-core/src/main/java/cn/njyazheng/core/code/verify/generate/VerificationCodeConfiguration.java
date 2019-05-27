package cn.njyazheng.core.code.verify.generate;

import cn.njyazheng.core.ConfigProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class VerificationCodeConfiguration {
    @Autowired
    private ConfigProperties configProperties;
    
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    private static final Logger logger= LoggerFactory.getLogger(VerificationCodeConfiguration.class);
    
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        if(!configProperties.getVerify().getCodeParams().isEmpty()){
            configProperties.getVerify().getCodeParams().forEach((key,value)->{
                logger.info("---------------------Kaptcha(key,value):"+"("+key+","+value+")");
                properties.setProperty(key,value);
            });
        }
        
//        Properties properties = new Properties();
//        // 图片边框
//        properties.setProperty("kaptcha.border", "yes");
//        // 边框颜色
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        // 字体颜色
//        properties.setProperty("kaptcha.textproducer.font.color", "red");
//        // 图片宽
//        properties.setProperty("kaptcha.image.width", "110");
//        // 图片高
//        properties.setProperty("kaptcha.image.height", "40");
//        // 字体大小
//        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        // session key
//        properties.setProperty("kaptcha.session.key", "code");
//        // 验证码长度
//        properties.setProperty("kaptcha.textproducer.char.length", "4");
//        // 字体
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
    
    @Bean
    //先找类型为GenerateVerificationCode的bean有就不配置,没有就配置
    @ConditionalOnMissingBean(GenerateVerificationCode.class)
    public GenerateVerificationCode getGenerateVerificationCode(){
        GenerateVerificationCodeImpl generateVerificationCode= new GenerateVerificationCodeImpl();
        generateVerificationCode.setConfigProperties(configProperties);
        generateVerificationCode.setDefaultKaptcha(defaultKaptcha);
        return generateVerificationCode;
    }
}
