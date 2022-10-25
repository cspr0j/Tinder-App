package org.tinder.servlets;

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

public class UserServlet extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    private final HashMap<String, Object> data = new HashMap<>();
    private final UserService userService = new UserService();
    private Long id;
    private int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.id = CookieUtil.getValue(req);
        List<User> users = userService.getNotLikedUser(id);

        if (count == users.size()) {
            count = 0;
            resp.sendRedirect("/liked");
        } else {
            User user = users.get(count);
            count++;

            data.put("user", user);
            freemarker.render("like-page.ftl", data, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        LikeService likeService = new LikeService(userId);

        boolean likeB = Boolean.parseBoolean(req.getParameter("like"));
        if (likeB) {
            likeService.save(new Like(this.id, userId));
        }
        resp.sendRedirect("/users");
    }
}
