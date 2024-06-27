package com.example.pipeline.valve;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;

public class WswAccessValve extends ValveBase {
    @Override
    public void invoke(Request request, Response response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        getNext().invoke(request, response);
    }
}
