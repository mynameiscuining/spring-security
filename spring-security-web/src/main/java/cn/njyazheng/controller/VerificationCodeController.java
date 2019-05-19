package cn.njyazheng.controller;

import cn.njyazheng.core.verify.code.GenerateVerificationCode;
import cn.njyazheng.core.verify.code.VerificationCode;
import cn.njyazheng.core.browser.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;

/**
 * 验证码
 * 1.根据随机数生成验证码
 * 2.将随机数放到session中
 * 3,将生成的图片放到响应中
 */
@RestController
public class VerificationCodeController {
    
    @Autowired
    private GenerateVerificationCode generateVerificationCode;
    
    @GetMapping("/verify/code")
    public ResponseEntity<byte[]> code(HttpServletRequest request)throws Exception{
        //把随机数放进session
        VerificationCode verificationCode = generateVerificationCode.generate(request);
        request.getSession().setAttribute(SessionKey.SESSION_KEY_VERIFY_CODE,verificationCode);
        HttpStatus statusCode = HttpStatus.OK;
        
        HttpHeaders headers= new HttpHeaders();
        headers.setCacheControl("no-cache");
        headers.setContentType(MediaType.IMAGE_JPEG);
        //时间戳
        headers.setLastModified(Instant.now().toEpochMilli());
        
        ResponseEntity<byte[]> response=null;
        try (ByteArrayOutputStream outputStream=new ByteArrayOutputStream()){
            ImageIO.write(verificationCode.getBufferedImage(),"jpg",outputStream);
            response = new ResponseEntity<byte[]>(outputStream.toByteArray(),headers , statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  response;
    }
}
