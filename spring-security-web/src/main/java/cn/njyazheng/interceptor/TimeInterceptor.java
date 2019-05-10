package cn.njyazheng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalTime;
@Component
//局限无法获取方法参数的具体的值
public class TimeInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeInterceptor.class);
    //进入控制器之前会进入 false拦截,true是放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("-----------------------TimeInterceptor preHandle");
        if(handler instanceof HandlerMethod ){
            LOGGER.info("-----------------------TimeInterceptor Controllername:"+((HandlerMethod)handler).getBean().getClass().getName());
        }
        request.setAttribute("start", LocalTime.now());
        return true;
    }
    
    //控制方法方法执行之后,如果控制器抛出异常此方法不会调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("-----------------------TimeInterceptor postHandle");
        LOGGER.info("-----------------------TimeInterceptor execute time:"+ Duration.between((LocalTime)request.getAttribute("start"),LocalTime.now()).toMillis()+"ms");
    }
    //控制方法方法执行之后,不过控制器方法是否正常运行,都会进入这个方法
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("-----------------------TimeInterceptor afterCompletion");
        LOGGER.info("-----------------------TimeInterceptor Exception is:"+ ex);
        LOGGER.info("-----------------------TimeInterceptor execute time:"+ Duration.between((LocalTime)request.getAttribute("start"),LocalTime.now()).toMillis()+"ms");
    }
}
