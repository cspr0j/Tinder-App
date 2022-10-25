package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.filter.LoginFilter;
import org.tinder.filter.RedirectFilter;
import org.tinder.service.UserService;
import org.tinder.servlets.*;
import org.tinder.utils.UserGenerator;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApp {
    static {
        UserService userService = new UserService();
        for (int i = 0; i < 10; i++) {
            userService.save(UserGenerator.generate());
        }
    }

    public static void main(String[] args) throws Exception {
        // getting Heroku port
        String webPort = System.getenv("PORT");

        if (webPort == null || webPort.isEmpty()) {
            // for local machine
            webPort = "8080";
        }
        Server server = new Server(Integer.parseInt(webPort));

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        contextHandler.addServlet(new ServletHolder(new LoginServlet()), "/");
        contextHandler.addServlet(new ServletHolder(new RegistrationServlet()), "/register");
        contextHandler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        contextHandler.addServlet(new ServletHolder(new UserServlet()), "/users");
        contextHandler.addServlet(new ServletHolder(new LikedServlet()), "/liked");
        contextHandler.addServlet(new ServletHolder(new MessageServlet()), "/messages/*");
        contextHandler.addServlet(new ServletHolder(new FileServlet()), "/templates/*");

        contextHandler.addFilter(LoginFilter.class, "/login/*", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(RedirectFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
