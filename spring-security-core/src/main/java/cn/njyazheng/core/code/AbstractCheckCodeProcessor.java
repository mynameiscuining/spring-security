package cn.njyazheng.core.code;

import org.springframework.web.context.request.ServletWebRequest;

public abstract class AbstractCheckCodeProcessor<T> implements CheckCodeProcessor {
    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {
        T validateCode=generate(servletWebRequest);
        save(servletWebRequest,validateCode);
        send(servletWebRequest,validateCode);
    }
    
    protected abstract void save(ServletWebRequest servletWebRequest, T validateCode);
    
    protected abstract T generate(ServletWebRequest servletWebRequest) ;
    
    protected abstract void send(ServletWebRequest servletWebRequest, T validateCode) throws Exception;
}
