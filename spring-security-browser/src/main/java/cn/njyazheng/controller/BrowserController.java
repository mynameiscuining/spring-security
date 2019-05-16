package cn.njyazheng.controller;

import cn.njyazheng.config.Browserproperties;
import cn.njyazheng.controller.vo.UnAuthorized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserController {
    //在执行认证跳转到/authentication/require之前,spring-security会把请求缓存到HttpSessionRequestCache
    private RequestCache requestCache=new HttpSessionRequestCache();
    private static final Logger logger= LoggerFactory.getLogger(BrowserController.class);
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    @Autowired
    private Browserproperties browserproperties;
    /**
     * 当需要身份认证时,跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public UnAuthorized requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //之前缓存请求
        SavedRequest savedRequest=requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String target=savedRequest.getRedirectUrl();
            logger.info("---------------------------------------引发请求的跳转是"+target);
            if (StringUtils.endsWithIgnoreCase(target,".html")){
                redirectStrategy.sendRedirect(request,response,browserproperties.getBrowser().getLoginPage());
            }
        }
        return new UnAuthorized("访问的服务需要身份认证");
    }
}
