package com.donation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.donation.constant.LoginVariable;
@WebFilter(urlPatterns={"/admin/*","/user/*"})
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.printf("过滤器实现");
        if(request.getSession().getAttribute(LoginVariable.LOGIN_USER) != null){
        	chain.doFilter(request,response);
        }else{
        	response.sendRedirect("/");
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
