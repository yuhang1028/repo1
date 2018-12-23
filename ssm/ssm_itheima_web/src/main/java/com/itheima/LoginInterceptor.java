package com.itheima;

import com.itheima.domain.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }
        if (user!= null && user.getUsername() !=null ){
            return true;
        }else {
            request.setAttribute("msg","请先登录!!");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        return false;
    }

}
