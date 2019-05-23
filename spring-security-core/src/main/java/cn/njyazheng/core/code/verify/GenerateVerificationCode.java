package cn.njyazheng.core.code.verify;



import javax.servlet.http.HttpServletRequest;

public interface GenerateVerificationCode {
     VerificationCode generate(HttpServletRequest request);
}
