package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class Authinteceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userServcie;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {

			// DefaultServletHandler 처리하는 경우 ( 정적 자원 접근)
			return true;
		}
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3 Handler Method 의 @Auth 받아오기

		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
	

		// 4. Handler Method에 @Auth가 없 으면 Type에 붙어 있는지 확인한다

		// 4. Handler Method에 @Auth가 없으면 Type에 붙어 있는지 확인한다(과제)
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. Type이나 Method 둘다 @Auth가 적용이 안되어 있는 경우
		if (auth == null) {
			return true;
		}

		// 5 . @Auth 가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {

			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7. admin일 경우
		String role = auth.role().toString();
		String[] divId = request.getRequestURI().toString().split("/");	
		String id = divId[2];
	

				
	
	
			if( id.equals(authUser.getId()) == false){   // admin이 아니므로 return false
				response.sendRedirect(request.getContextPath());
				return false;
			}
		
		

		return true;
	}

}
