package org.tinder.servlets;

import org.tinder.entities.User;
import org.tinder.service.UserService;
import org.tinder.utils.Freemarker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    private final Freemarker freemarker = new Freemarker();
    private final UserService usersService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        freemarker.render("register.html", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String photoUrl = req.getParameter("url");
        Integer age = Integer.valueOf(req.getParameter("age"));
        String gender = req.getParameter("gender");

        User user = new User(email, password, name, surname, photoUrl, age, gender);
        if (usersService.getById(user.getEmail()) != null) {
            //TODO add exception
            throw new RuntimeException();
        }
        usersService.save(user);

        resp.sendRedirect("/login");
    }
}
