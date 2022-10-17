package org.tinder.servlets;

import org.tinder.entities.User;
import org.tinder.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    List<User> userList = new ArrayList<>();

    List<Integer> likedUsers = new ArrayList<>();
    private final Freemarker freemarker = new Freemarker();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        data.put("userList", userList);
        freemarker.render("users.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dislike = req.getParameter("dislike");
        String like = req.getParameter("like");
        System.out.println(dislike);
        System.out.println(like);

//        if (!req.getParameter("like").isEmpty()) {
//            likedUsers.add()
//        }
    }
}
