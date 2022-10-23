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

public class LikedServletViaList extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    HashMap<String, Object> data = new HashMap<>();
    UserService userService = new UserService();
    List<User> users = userService.getAllActive();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = CookieUtil.getValue(req);
        LikeService service = new LikeService(id);
        if (users.isEmpty()) users = userService.getAllActive();

        users.removeIf(user -> service.getAllLikesId().contains(user.getId()) || user.getId().equals(id));
        System.out.println(users.get(0));
        data.put("user", users.get(0));
        freemarker.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        System.out.println(userId);
        System.out.println(req.getParameter("dislike") == null);
        resp.sendRedirect("/users");
    }
}
