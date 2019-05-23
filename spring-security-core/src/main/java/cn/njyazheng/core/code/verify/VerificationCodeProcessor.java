package cn.njyazheng.core.code.verify;

import cn.njyazheng.core.browser.SessionKey;
import cn.njyazheng.core.code.AbstractCheckCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("verificationCodeProcessor")
public class VerificationCodeProcessor extends AbstractCheckCodeProcessor<VerificationCode> {
    @Autowired
    private  GenerateVerificationCode generateVerificationCode;
    
    @Override
    protected void save(ServletWebRequest servletWebRequest, VerificationCode validateCode) {
       servletWebRequest.getRequest().getSession().setAttribute(SessionKey.SESSION_KEY_VERIFY_CODE,validateCode);
    }
    
    @Override
    protected VerificationCode generate(ServletWebRequest servletWebRequest) {
        return generateVerificationCode.generate(servletWebRequest.getRequest());
    }
    
    @Override
    protected void send(ServletWebRequest servletWebRequest, VerificationCode validateCode) throws Exception {
        ImageIO.write(validateCode.getBufferedImage(),"jpg",servletWebRequest.getResponse().getOutputStream());
    }
}
