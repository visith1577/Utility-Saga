package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(
        urlPatterns = "/*"
)
public class LogFilter implements Filter {

    private FilterConfig filterConfigObj;
    private static final Logger LOGGER = Logger.getLogger(LogFilter.class.getName());

    public void init(FilterConfig filterConfigObj) {
        this.filterConfigObj = filterConfigObj;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String remoteAddress = servletRequest.getRemoteAddr();

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        String protocol = servletRequest.getProtocol();


        filterConfigObj.getServletContext().log("Logging Filter Servlet");
        filterConfigObj.getServletContext().log("******* *******************");
        filterConfigObj.getServletContext().log("User Logged ! " + "User IP: " + remoteAddress + " Resource File: " + uri + " Protocol: " + protocol);

        long startTime = System.currentTimeMillis();

        try {
            // Log the request details
            logRequestDetails(servletRequest);

            // Continue with the filter chain
            filterChain.doFilter(servletRequest, servletResponse);

            // Log the response details
            logResponseDetails(servletResponse);

        } catch (Exception e) {
            // Log any exceptions that occur during processing
            LOGGER.log(Level.SEVERE, "Exception in LoggingFilter", e);
            throw e; // Re-throw the exception to ensure it's propagated
        } finally {
            // Calculate and log the processing time
            long processingTime = System.currentTimeMillis() - startTime;
            LOGGER.info("Request processed in " + processingTime + " ms");
        }
    }

    private void logRequestDetails(ServletRequest request) {
        // Log request details such as URL, parameters, headers, etc.
        LOGGER.info("Request URL: " + ((HttpServletRequest) request).getRequestURL());
    }

    private void logResponseDetails(ServletResponse response) {
        // Log response details such as status code, headers, etc.
        LOGGER.info("Response Status Code: " + ((HttpServletResponse) response).getStatus());
    }

    public void destroy(){}
}
