package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static utils.Constant.SOLAR_AUTHENTICATION_KEY_FLAG;
import static utils.Constant.SOLAR_AUTH_RESTRICT_URL_LIST;

@WebFilter(
        urlPatterns = {"/public/HTML/solar/*"},
        filterName = "SolarAuth-filter",
        description = "Authentication middleware for Solar Company"
)
public class SolarAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        // Check if the user is authenticated
        boolean isAuthenticated = isSolarAuthenticated(session, SOLAR_AUTHENTICATION_KEY_FLAG);

        // Check if the requested URL should be restricted
        String requestURI = httpRequest.getRequestURI();
        if (isRestrictedURL(requestURI) && !isAuthenticated) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + Constant.LOGIN_SOLARLOGIN_JSP);
            return;
        }
        // Allow the request to proceed
        chain.doFilter(request, response);
    }

    private boolean isSolarAuthenticated(HttpSession session, String key) {
        if (session.getAttribute(key) != null) {
            return (boolean) session.getAttribute(key);
        }
        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


    private boolean isRestrictedURL(String requestURI) throws IOException {
        // Define the list of restricted URLs
        PropertyReader propertyReader = new PropertyReader(Constant.APPLICATION_PROPERTY_FILE_NAME);
        String[] restrictedURLs = propertyReader.get(SOLAR_AUTH_RESTRICT_URL_LIST).toString().trim().split(",");

        // Check if the requested URL matches any of the restricted URLs
        for (String url : restrictedURLs) {
            if (requestURI.endsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
