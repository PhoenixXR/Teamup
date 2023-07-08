package com.example.Teamup_web.servlet;

import com.example.Teamup_web.dao.UserDao;
import com.example.Teamup_web.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

/**
 * @author phoeniX.R
 * 登录
 */
@WebServlet(name = "SignIn", value = "/SignIn")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException,ServletException
    {
        this.doPost(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/plain;charset=utf-8");

        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");

        UserDao userDao = new UserDao();
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        if (!"".equals(name) && !"".equals(password)) {
            if (!userDao.query(user)) {
                httpServletResponse.sendError(204, "query failed.");
            } else {
                System.out.println("登录成功");
            }
        }
    }
}


