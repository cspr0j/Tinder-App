package org.tinder.servlets;

import org.tinder.entities.User;
import org.tinder.service.UserService;
import org.tinder.utils.Freemarker;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    private final UserService usersService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("user_id", UUID.randomUUID().toString());
        System.out.printf("Cookie name = %s, id = %s\n", cookie.getName(), cookie.getValue());
        resp.addCookie(cookie);
        HashMap<String, Object> data = new HashMap<>();
        freemarker.render("login.html", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = usersService.get(login);
        if (user == null || !user.getPassword().equals(password) || !user.isActive()) {
            //TODO add exception
//            throw new RuntimeException();
            resp.sendRedirect("templates/login-error.html");
        }

        Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
        //todo bunu nezerde saxla, filter qoyan zaman bu islemsesine mane ola biler
//        cookie.setMaxAge(0);
        System.out.printf("Cookie name = %s, id = %s\n", cookie.getName(), cookie.getValue());
        resp.addCookie(cookie);

        resp.sendRedirect("/users");
    }
}
