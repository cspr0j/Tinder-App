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

public class LikedServletViaSQL extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    private Long id;
    HashMap<String, Object> data = new HashMap<>();
    UserService userService = new UserService();
    LikeService service;
//    List<User> users = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = CookieUtil.getValue(req);
        User user = userService.getNotLikedUserV2(id);
        if (user == null) {
            resp.sendRedirect("/liked");
            return;
        }

        data.put("user", user);
        freemarker.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));

        service = new LikeService(new LikeDAO(id));
        boolean likeB = Boolean.parseBoolean(req.getParameter("like"));
        if (likeB) {
            service.save(new Like(id, userId));
        } else {
            System.out.println("dislike!!!");
        }
        resp.sendRedirect("/users");
    }
}
