package org.nishkarma.cas.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void afterPropertiesSet() throws Exception {
	}

    @Override
    public final boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object o) throws Exception {
    	
    	MyClientInfoHolder.setClientInfo(new MyClientInfo(request));
    	return true;
    }
    
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}    
}
