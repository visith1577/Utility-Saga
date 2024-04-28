package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(
        urlPatterns = {"/public/HTML/user/*", "/public/HTML/pages/*", "/public/HTML/login/*", "/user/*"},
        filterName ="Auth-filter",
        description = "Authentication middleware"
)
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Authentication Filter <--------__in__-------->");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        String action = req.getServletPath();

//        /public/HTML/login/userLogin.jsp

        if(action.matches("^/public/HTML/login/.*"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            Object isLogged = session.getAttribute("isLoggedIn");
            String currRole = (String) session.getAttribute("ROLE");
            if (isLogged != null){
                boolean isLoggedIn = (boolean) isLogged;
                if (isLoggedIn && currRole.equals("USER")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            System.out.println("wrong pwd");
            resp.sendRedirect(req.getContextPath() + "/public/HTML/login/userSelector.jsp");
        }
        System.out.println("Authentication Filter <--------__end__-------->");
    }
}


