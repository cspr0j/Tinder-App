package org.tinder.servlets;

import org.tinder.utils.Freemarker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final Freemarker f = new Freemarker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();

        f.render("login.ftl", data, resp);
    }
}
