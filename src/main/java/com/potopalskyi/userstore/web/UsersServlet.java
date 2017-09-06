package com.potopalskyi.userstore.web;

import com.potopalskyi.userstore.entity.User;
import com.potopalskyi.userstore.service.IUserService;
import com.potopalskyi.userstore.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet{
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> userList = userService.getAll();
            resp.getWriter().write(PageGenerator.getInstance().createPage("AllUsers.html", userList));
        } catch (Exception e){
            resp.getWriter().write("Please try again later");
        }
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
