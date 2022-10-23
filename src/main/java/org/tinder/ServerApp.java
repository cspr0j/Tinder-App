package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.servlets.*;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(6067);

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        contextHandler.addServlet(new ServletHolder(new RegistrationServlet()), "/register");
//        contextHandler.addServlet(new ServletHolder(new LikedServletViaList()), "/users");
        contextHandler.addServlet(new ServletHolder(new LikedServletViaSQL()), "/users");
        contextHandler.addServlet(new ServletHolder(new ListServlet()), "/liked");
        contextHandler.addServlet(new ServletHolder(new MessageServlet()), "/messages/*");
        contextHandler.addServlet(new ServletHolder(new FileServlet()), "/templates/*");

        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
