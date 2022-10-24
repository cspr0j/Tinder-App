package org.tinder.servlets;

import org.tinder.utils.Freemarker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class FileServlet extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        freemarker.render(req.getPathInfo(), new HashMap<>(), resp);
    }
}