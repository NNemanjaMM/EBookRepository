package com.nemanjam.ebook.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nemanjam.ebook.entity.db.UserEntity;

public class SessionInterceptorSubscriber extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request, 
    		HttpServletResponse response, Object handler) throws Exception {
        
		HttpSession session = request.getSession();
		
		if (session != null) {			
			UserEntity user = (UserEntity) session.getAttribute("sessionUser");
			if (user != null) {
				String type = user.getType();
				if (type.equals("s") || type.equals("a")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}			
		} else {
			return false;
		}		
    }
	
}
