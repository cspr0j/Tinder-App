package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.servlets.LoginServlet;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        LoginServlet loginServlet = new LoginServlet();

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
