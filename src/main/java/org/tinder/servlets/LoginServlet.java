package org.tinder.servlets;

import org.tinder.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("user_id", UUID.randomUUID().toString());
        System.out.printf("Cookie name = %s, id = %s\n", cookie.getName(), cookie.getValue());
        resp.addCookie(cookie);
        HashMap<String, Object> data = new HashMap<>();
        freemarker.render("login.html", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("email = " + email);
        System.out.println("psw = " + password);

        String email1 = "ulvi.bakirli@gmail.com";
        String password1 = "superpsw";

        if (email.equals(email1) && password.equals(password1)) {
            freemarker.render("welcome.html", data, resp);
        } else{
            freemarker.render("notwelcome.html", data, resp);
        }
    }
}
