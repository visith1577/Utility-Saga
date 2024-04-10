package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserRAdmin;

import java.io.IOException;

@WebFilter(
        urlPatterns = {"/public/HTML/water/admin/*", "/public/HTML/pages/*", "/public/HTML/login/*"},
        filterName ="Auth-filter__water_admin",
        description = "Authentication middleware"
)
public class AdminWaterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Authentication Filter <--------__in__-------->");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        String action = req.getServletPath();

//        /public/HTML/login/userLogin.jsp

        if(action.matches("^/public/HTML/login/.*") || action.matches("^/public/HTML/pages/.*"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            Object isLogged = session.getAttribute("isLoggedIn");
            Object role = session.getAttribute("ROLE");
            if (isLogged != null ){
                boolean isLoggedIn = (boolean) isLogged;
                UserRAdmin.Role currRole = UserRAdmin.Role.valueOf(role.toString());
                if (isLoggedIn && currRole == UserRAdmin.Role.MAIN) {
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
