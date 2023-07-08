package com.example.Teamup_web.servlet;
import com.example.Teamup_web.dao.UserDao;
import com.example.Teamup_web.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/**
 * @author phoeniX.R
 * 注册
 */
@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException,ServletException
    {
        this.doPost(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException,ServletException
    {
        /**
         * 设定编码防止中文乱码
         * 设置相应类型为html,编码为utf-8
         * 根据name获取参数
         * 根据password获取参数
         */
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/plain;charset=utf-8");

        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        System.out.println(name+"\n"+password);
        UserDao userDao = new UserDao();
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        //防止给数据库null的，数据库因为偷懒设计上是允许空的，还失败204
        if (!"".equals(name) && !"".equals(password)) {
            if (!userDao.add(user)) {
                httpServletResponse.sendError(204, "add failed.");
            }
        }
    }
}
