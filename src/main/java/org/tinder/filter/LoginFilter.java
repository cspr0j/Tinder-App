package org.tinder.filter;

import org.eclipse.jetty.http.HttpMethod;
import org.tinder.entities.User;
import org.tinder.service.UserService;
import org.tinder.utils.Freemarker;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginFilter implements Filter {

    private final Freemarker freemarker = new Freemarker();
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserService userService = new UserService();
            User user = userService.getByUsername(login);
            if (user == null || !user.getPassword().equals(password) || !user.isActive()) {
                freemarker.render("login-error.html", new HashMap<>(), response);
                return;
            }
            Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
            response.addCookie(cookie);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
