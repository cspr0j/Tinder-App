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
    private final HashMap<Long, Integer> mapId = new HashMap<>();
    private final UserService userService = new UserService();
    private int count;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = CookieUtil.getValue(req);
        if (!mapId.containsKey(id)) {
            mapId.put(id, 0); // id -> count
        }

        List<User> users = userService.getNotLikedUser(id);
        if (count == users.size() - 1) {
            count = 0;
            mapId.put(id, count);
            resp.sendRedirect("/liked");
        } else {
            User user = users.get(mapId.get(id));

            data.put("user", user);
            freemarker.render("like-page.ftl", data, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = CookieUtil.getValue(req);
        Long userId = Long.valueOf(req.getParameter("userId"));
        LikeService likeService = new LikeService(userId);

        boolean isLiked = Boolean.parseBoolean(req.getParameter("like"));
        if (isLiked) {
            likeService.save(new Like(id, userId));
        }

        count = mapId.get(id);
        mapId.put(id, count + 1);
        resp.sendRedirect("/users");
    }
}
