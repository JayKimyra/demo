package com.example.demo;

import com.example.demo.hibernate.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/userview/*")
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("RoleFilter!");
        System.out.println(user);
        if (user != null && user.getRole().equals("admin")){
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            ((HttpServletResponse) servletResponse).sendError(404);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
