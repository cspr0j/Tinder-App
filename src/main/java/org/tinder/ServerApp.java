package org.tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.filters.LoginFilter;
import org.tinder.servlets.*;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;

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
        contextHandler.addServlet(new ServletHolder(new MessageServlet()), "/message/*");
        contextHandler.addServlet(IndexServlet.class, "/");

        contextHandler.addFilter(LoginFilter.class, "/login", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(LoginFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(LoginFilter.class, "/register", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(LoginFilter.class, "/likes", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(LoginFilter.class, "/templates/*", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(LoginFilter.class, "/message/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(contextHandler);

        server.start();
        server.join();

    }
}
