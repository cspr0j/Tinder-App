package org.tinder.servlets;

import org.tinder.entities.Message;
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

    private final HashMap<String, Object> data = new HashMap<>();
    private final UserService userService = new UserService();
    private final Freemarker freemarker = new Freemarker();
    private Long senderID;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        senderID = CookieUtil.getValue(req);
        Long targetId = Long.parseLong(req.getPathInfo().replace("/", ""));
        MessageService messageService = new MessageService(senderID);

        List<Message> messages = messageService.getAllItemsByTargetId(senderID, targetId);

        data.put("sender", userService.getByUsername(senderID));
        data.put("receiver", userService.getByUsername(targetId));
        data.put("messages", messages);
        freemarker.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello");
    }
}
