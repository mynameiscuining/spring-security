package cn.njyazheng.core.code.sms.generate;

import cn.njyazheng.core.ConfigProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class GenerateSmsCodeImpl implements GenerateSmsCode {
    @Autowired
    ConfigProperties configProperties;
    
    @Override
    public SmsCode generate(HttpServletRequest request) {
        String code=RandomStringUtils.randomNumeric(6);
        return new SmsCode(code, LocalDateTime.now().plusMinutes(configProperties.getSms().getExpire()));
    }
}
