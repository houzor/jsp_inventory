package com.houzer.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.houzer.pojo.User;
public class SysFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //过滤器，从Session中获取用户
        User user = (User) request.getSession().getAttribute("usersession");

        if(user==null){//已经被移除或者注销了，或者未登录
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        } else {
            
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    public void destroy() {

    }
}
