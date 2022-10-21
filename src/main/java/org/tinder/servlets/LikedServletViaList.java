package org.tinder.servlets;

import org.tinder.dao.LikeDAO;
import org.tinder.entities.Like;
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

public class LikedServletViaList extends HttpServlet {
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
        users.removeIf(user -> service.getAllLikesId().contains(user.getId()) || user.getId().equals(id));

        data.put("user", users.get(0));
        freemarker.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        boolean likeB = Boolean.parseBoolean(req.getParameter("like"));
        if (likeB) {
            service.save(new Like(id, userId));
        } else {

        }

        users.remove(0);
        if (users.isEmpty()) {
            resp.sendRedirect("/liked");
        } else {
            resp.sendRedirect("/users");
        }
    }
}
