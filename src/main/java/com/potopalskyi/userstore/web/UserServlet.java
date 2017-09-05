package com.potopalskyi.userstore.web;

import com.potopalskyi.userstore.entity.User;
import com.potopalskyi.userstore.service.IUserService;
import com.potopalskyi.userstore.web.util.PageGenerator;
import com.potopalskyi.userstore.web.util.RequestToUserParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("http://localhost:8080/addUser".equals(req.getRequestURL().toString())){
            resp.getWriter().write(PageGenerator.getInstance().createPage("templates" + File.separator + "AddUser.html", null));
            return;
        }
        try {
            List<User> userList = userService.getAll();
            resp.getWriter().write(PageGenerator.getInstance().createPage("templates" + File.separator + "AllUsers.html", userList));
        } catch (Exception e){
            resp.getWriter().write("Please try again later");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = RequestToUserParser.getUserFromRequest(req);
        userService.add(user);
        resp.setContentType("text/html;charset=utf-8");
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}
