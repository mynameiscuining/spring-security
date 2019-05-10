package cn.njyazheng.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
@Component
//rest异步服务使用
public class AsyncIntercepter implements CallableProcessingInterceptor {
}
