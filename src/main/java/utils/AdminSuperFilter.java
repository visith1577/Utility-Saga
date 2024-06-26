package utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserRAdmin;

import java.io.IOException;

@WebFilter(
        urlPatterns = {"/public/HTML/superadmin/*", "/public/HTML/pages/*", "/public/HTML/login/*", "/super-admin/*"},
        filterName ="Auth-filter__Super_admin",
        description = "Authentication middleware"
)
public class AdminSuperFilter implements Filter {
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
            if (session != null) {
                Object isLogged = session.getAttribute("isLoggedIn");
                Object role = session.getAttribute("ROLE");

                if (isLogged != null && role != null) {
                    boolean isLoggedIn = (boolean) isLogged;
                    UserRAdmin.Role currRole = UserRAdmin.Role.valueOf(role.toString());

                    if (isLoggedIn && currRole == UserRAdmin.Role.SUPERADMIN) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
            System.out.println("User not logged in or not a super admin");
            resp.sendRedirect(req.getContextPath() + "/public/HTML/login/administratorLogin.jsp");
        }
        System.out.println("Authentication Filter <--------__end__-------->");
    }
}
