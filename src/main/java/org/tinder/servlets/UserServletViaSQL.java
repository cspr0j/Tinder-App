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

public class UserServletViaSQL extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    HashMap<String, Object> data = new HashMap<>();
    UserService userService = new UserService();
//    List<User> users = new ArrayList<>();
    long id;
    int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllActive();

//        id = user.getId();
//        userList.add(user);
        
        if (count == users.size()) {
            //todo liked list html gonderilsin
            count = 0;
            resp.sendRedirect("/likes");
        } else {
            User user = users.get(count);
            HashMap<String, Object> data = new HashMap<>();
            data.put("users", user);
            count++;
            freemarker.render("users.ftl", data, resp);
        }


        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");
//        String dislike = req.getParameter("dislike");

        if (like != null && !like.isEmpty()) {
            Long id = CookieUtil.getValue(req);
            LikeService likeService = new LikeService(id);
            boolean save = likeService.save(new Like(id, this.id));
            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/users");
        }

    }
}
