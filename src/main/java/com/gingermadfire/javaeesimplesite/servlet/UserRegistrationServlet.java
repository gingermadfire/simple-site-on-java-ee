package com.gingermadfire.javaeesimplesite.servlet;

import com.gingermadfire.javaeesimplesite.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/registration")
public class UserRegistrationServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.sendRedirect("registration.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        userService.register(req, resp);
    }
}
