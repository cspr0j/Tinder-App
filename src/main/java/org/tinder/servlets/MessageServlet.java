package org.tinder.servlets;

import org.tinder.entities.Message;
import org.tinder.entities.User;
import org.tinder.service.MessageService;
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

public class MessageServlet extends HttpServlet {




    private final UserService userService = new UserService();
    private final Freemarker freemarker = new Freemarker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idFrom = CookieUtil.getValue(req);
        final MessageService messageService = new MessageService(idFrom);
        String idS = req.getPathInfo().substring(1);
        Long idTo = Long.parseLong(idS);

        System.out.println("id from = " + idFrom);
        System.out.println("id to = " + idTo);

        List<Message> messages = messageService.getAllItemsByTargetId(idTo);
        User receiver = userService.get(idTo);
        User sender = userService.get(idFrom);

        System.out.println("messages = " + messages);

        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        data.put("receiver", receiver);
        data.put("sender", sender);
        freemarker.render("chat.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
