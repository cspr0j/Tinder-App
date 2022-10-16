package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.servlets.LikedServlet;
import org.tinder.servlets.LoginServlet;
import org.tinder.servlets.UserServlet;

public class    ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        LoginServlet loginServlet = new LoginServlet();
        UserServlet userServlet = new UserServlet();
        LikedServlet likedServlet = new LikedServlet();

        ServletContextHandler contextHandler = new ServletContextHandler();

        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        contextHandler.addServlet(new ServletHolder(userServlet), "/users");
        contextHandler.addServlet(new ServletHolder(likedServlet), "/liked");

        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
