package cn.njyazheng.core.code.sms.generate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsCodeConfiguration {
    @Bean
    @ConditionalOnMissingBean(value = SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSenderImpl();
    }
    
    @Bean
    @ConditionalOnMissingBean(value = GenerateSmsCode.class)
    public GenerateSmsCode generateSmsCode(){
        return new GenerateSmsCodeImpl();
    }

}


