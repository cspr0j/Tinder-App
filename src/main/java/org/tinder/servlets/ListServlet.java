package org.tinder.servlets;

import org.tinder.entities.User;
import org.tinder.service.LikeService;
import org.tinder.service.UserService;
import org.tinder.utils.CookieUtil;
import org.tinder.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ListServlet extends HttpServlet {

    private final Freemarker freemarker = new Freemarker();
    private final HashMap<String, Object> data = new HashMap<>();
    private Long id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = CookieUtil.getValue(req);
        UserService userService = new UserService();
        List<User> users = userService.getAllActive();
        LikeService service = new LikeService(id);

        users.removeIf(user -> !service.getAllLikesId().contains(user.getId()) || user.getId().equals(id));

        data.put("users", users);
        freemarker.render("list.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/messages/" + req.getParameter("id"));
    }
}
