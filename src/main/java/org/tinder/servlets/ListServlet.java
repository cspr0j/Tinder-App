package org.tinder.servlets;

import org.tinder.dao.LikeDAO;
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
    private Long id;
    HashMap<String, Object> data = new HashMap<>();
    UserService userService = new UserService();
    List<User> users = userService.getAllActive();
    LikeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = CookieUtil.getValue(req);
        service = new LikeService(new LikeDAO(id));
        users.removeIf(user -> !service.getAllLikesId().contains(user.getId()) || user.getId().equals(id));

        System.out.println(users);
        data.put("users", users);
        freemarker.render("list.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/messages/"+req.getParameter("id"));
    }
}
