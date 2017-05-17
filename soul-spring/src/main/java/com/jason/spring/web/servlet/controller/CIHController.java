package com.jason.spring.web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

 
/**Spring以一种抽象的方式实现了控制器概念，这样可以支持不同类型的控制器。
 * Spring本身包含表单控制器、命令控制器、向导型控制器等多种多样的控制器。
 * 可以发现Controller接口仅仅声明了一个方法，它负责处理请求并返回合适的模型和视图。Spring MVC实现的基础 就是这三个概念：Mdel、View（ModelAndView）以及 Controller。虽然 Controller接口是完全抽象的，但Spring也提供了许多你可能会用到的控制器。
 * Controller接口仅仅定义了每个控制器都必须提供的基本功能： 处理请求并返回一个模型和一个视图。 
* @ClassName: CIHController
* @author CIH CIHUnKnown@Gmail.com
* @date 2013-4-19 上午12:41:06
* 
*/
public class CIHController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView=new ModelAndView("home");
		modelAndView.addObject("hello", "oh Yes!");
		return modelAndView;
	}

}
