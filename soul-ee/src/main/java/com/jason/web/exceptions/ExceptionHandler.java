package com.jason.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
* @ClassName: ExceptionHandler
* @Description: TODO(自定义异常处理器 )
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午10:47:15
* 
*/
public class ExceptionHandler implements HandlerExceptionResolver {
	private Log logger=LogFactory.getLog(ExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error(ex);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			return new ModelAndView("error/error-business", model);
		}else if(ex instanceof ParameterException) {
			return new ModelAndView("error/error-parameter", model);
		} else {
			return new ModelAndView("error/error", model);
		}
	}
}
