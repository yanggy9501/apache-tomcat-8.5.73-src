package com.example.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter-----init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter-----doFilter");
        chain.doFilter(request,response);
    }

    public void destroy() {
        System.out.println("MyFilter----destroy");
    }
}
