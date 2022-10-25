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
import java.util.Set;
import java.util.stream.Collectors;

public class LikeServlet extends HttpServlet {

    private final Freemarker freemarker = new Freemarker();
    private final UserService userService = new UserService();
    private long id;
    private final LikeService likeService = new LikeService(id);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = CookieUtil.getValue(req);

        List<Long> likeList = likeService.getByID(id);
//        List<User> likedUsers = likeList.stream()
//                .map(like -> like.getLikedUserId())
//                .map(likedUserID -> userService.get(likedUserID))
//                .collect(Collectors.toList());
        System.out.println("likelist = " + likeList);

//        List<Long> longList = likeList.stream()
//                .map(like -> like.getLikedUserId())
//                .collect(Collectors.toList());
//        System.out.println("longlist = " + longList);

//        List<User> likedUsers = longList.stream()
//                .map(likedID -> userService.get(likedID))
//                .collect(Collectors.toList());
        List<User> likedUsers = likeList.stream()
                .map(ID -> userService.get(ID))
                .collect(Collectors.toList());


        System.out.println("liked users = " + likedUsers);

        HashMap<String, Object> data = new HashMap<>();
        data.put("likedUsers", likedUsers);
        freemarker.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
