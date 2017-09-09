package com.potopalskyi.userstore.web;

import com.potopalskyi.userstore.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet{
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        userService.delete(id);
        resp.sendRedirect("/users");
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
