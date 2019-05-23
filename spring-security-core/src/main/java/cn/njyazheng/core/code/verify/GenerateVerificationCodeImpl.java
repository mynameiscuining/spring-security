package cn.njyazheng.core.code.verify;

import cn.njyazheng.core.ConfigProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.regex.Pattern;

public class GenerateVerificationCodeImpl implements GenerateVerificationCode {
    
    private DefaultKaptcha defaultKaptcha;
    
    ConfigProperties configProperties;
    
    @Override
    public VerificationCode generate(HttpServletRequest request) {
        String widthstr =request.getParameter("width");
        String heightstr =request.getParameter("height");
        Pattern digit = Pattern.compile("\\d+");
        
        Properties properties=defaultKaptcha.getConfig().getProperties();
        if(!StringUtils.isEmpty(widthstr)&&digit.matcher(widthstr).matches()){
            properties.setProperty("kaptcha.image.width",widthstr);
        }else {
            setDefault("kaptcha.image.width",properties,digit);
        }
        if(!StringUtils.isEmpty(heightstr)&&digit.matcher(heightstr).matches()){
            properties.setProperty("kaptcha.image.height",heightstr);
        }else {
            setDefault("kaptcha.image.height",properties,digit);
        }
        VerificationCode verificationCode = new VerificationCode();
        //生成验证码
        String code = defaultKaptcha.createText();
        verificationCode.setCode(code);
        verificationCode.setBufferedImage(defaultKaptcha.createImage(code));
        verificationCode.setExpireTime(LocalDateTime.now().plusMinutes(configProperties.getVerify().getExpire()));
        return verificationCode;
    }
    private void setDefault(String key,Properties properties,Pattern digit){
         String num =configProperties.getVerify().getCodeParams().get(key);
         if(!StringUtils.isEmpty(num)&&digit.matcher(num).matches()){
             properties.setProperty(key,num);
         }else {
             properties.remove(key);
         }
    }
    
    public DefaultKaptcha getDefaultKaptcha() {
        return defaultKaptcha;
    }
    
    public void setDefaultKaptcha(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }
    
    public ConfigProperties getConfigProperties() {
        return configProperties;
    }
    
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }
}
