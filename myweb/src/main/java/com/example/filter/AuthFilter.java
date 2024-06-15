package com.example.filter;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthFilter-----init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthFilter-----doFilter");
        chain.doFilter(request,response);
    }

    public void destroy() {
        System.out.println("AuthFilter----destroy");
    }
}
