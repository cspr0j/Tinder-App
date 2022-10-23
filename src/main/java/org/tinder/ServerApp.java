package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.servlets.*;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        contextHandler.addServlet(new ServletHolder(new RegistrationServlet()), "/register");
//        contextHandler.addServlet(new ServletHolder(new LikedServletViaList()), "/users");
        contextHandler.addServlet(new ServletHolder(new UserServletViaSQL()), "/users");
        contextHandler.addServlet(new ServletHolder(new FileServlet()), "/templates/*");
        contextHandler.addServlet(new ServletHolder(new LikeServlet()), "/likes");

        server.setHandler(contextHandler);

        server.start();
        server.join();

    }
}
