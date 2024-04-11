package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


@WebFilter(filterName = "data-logging", urlPatterns = "/*")
public class DataFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Check for GET request
        if (httpRequest.getMethod().equals("GET")) {
            logRequestData(httpRequest, "GET");
        }

        // Check for POST request
        if (httpRequest.getMethod().equals("POST")) {
            logRequestData(httpRequest, "POST");
        }

        chain.doFilter(request, response);
    }

    private void logRequestData(HttpServletRequest request, String method) {

        // Access request parameters
        StringBuilder sb = new StringBuilder("[" + method + "] Request Data:");
        for (String parameterName : request.getParameterMap().keySet()) {
            String[] parameterValues = request.getParameterValues(parameterName);
            sb.append("\n  ").append(parameterName).append(": ");
            for (String value : parameterValues) {
                sb.append(value).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }

        System.out.println(sb);
    }
}
