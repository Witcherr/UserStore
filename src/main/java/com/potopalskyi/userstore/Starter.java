package com.potopalskyi.userstore;

import com.potopalskyi.userstore.dao.PhoneDao;
import com.potopalskyi.userstore.dao.UserDao;
import com.potopalskyi.userstore.dao.jdbc.JdbcPhoneDao;
import com.potopalskyi.userstore.dao.jdbc.JdbcUserDao;
import com.potopalskyi.userstore.service.impl.PhoneService;
import com.potopalskyi.userstore.service.impl.UserService;
import com.potopalskyi.userstore.web.UserDeleteServlet;
import com.potopalskyi.userstore.web.UserServlet;
import com.potopalskyi.userstore.web.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {

    public static void main(String[] args) throws Exception {
        UserDao userDao = new JdbcUserDao();
        PhoneDao phoneDao = new JdbcPhoneDao();

        PhoneService phoneService = new PhoneService();
        phoneService.setPhoneDao(phoneDao);

        UserService userService = new UserService();
        userService.setUserDao(userDao);
        userService.setPhoneService(phoneService);

        UserServlet userServlet = new UserServlet();
        userServlet.setUserService(userService);

        UsersServlet usersServlet = new UsersServlet();
        usersServlet.setUserService(userService);

        UserDeleteServlet userDeleteServlet = new UserDeleteServlet();
        userDeleteServlet.setUserService(userService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(userServlet), "/addUser");
        context.addServlet(new ServletHolder(usersServlet), "/users");
        context.addServlet(new ServletHolder(userDeleteServlet), "/delete");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
