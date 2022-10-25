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
    private MessageService messageService;
    private Long senderID;
    private Long targetId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        senderID = CookieUtil.getValue(req);
        targetId = Long.parseLong(req.getPathInfo().replace("/", ""));
        messageService = new MessageService(senderID);

        List<Message> messages = messageService.getAllItemsByTargetId(senderID, targetId);

        data.put("messages", messages);
        data.put("sender", userService.getById(senderID));
        data.put("receiver", userService.getById(targetId));
        freemarker.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("message");
        Message msg = new Message(senderID, targetId, text);
        if (!text.isEmpty()) {
            messageService.save(msg);
        }
        resp.sendRedirect("/messages/" + targetId);
    }
}
