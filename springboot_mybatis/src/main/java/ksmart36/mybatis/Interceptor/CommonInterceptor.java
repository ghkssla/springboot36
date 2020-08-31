package ksmart36.mybatis.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class CommonInterceptor implements HandlerInterceptor{
	
	
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);	

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 요청 후 컨트롤러 가기전
		
		log.info("CommonInterceptor.java =========================================== Start");
		log.info("ACCESS Info ====================================================== Start");
		log.info("Port       ::::: {} ", request.getLocalPort());
		log.info("ServerName ::::: {} ", request.getServerName());
		log.info("Method     ::::: {} ", request.getMethod());
		log.info("RequestURI ::::: {} ", request.getRequestURI());
		log.info("ACCESS Info ====================================================== End");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 후 뷰 전
		
		log.info("CommonInterceptor.java =========================================== End");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	/*
	 * @Override public void afterCompletion(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, Exception ex) throws Exception
	 * { // 타임리프 이후 HandlerInterceptor.super.afterCompletion(request, response,
	 * handler, ex); }
	 */
}
