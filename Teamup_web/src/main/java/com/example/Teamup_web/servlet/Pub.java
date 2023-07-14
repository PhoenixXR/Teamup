package com.example.Teamup_web.servlet;

import com.example.Teamup_web.dao.InfoDao;
import com.example.Teamup_web.entity.Info;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * @author phoeniX.R
 * 发布
 */
@WebServlet(name = "Pub", value = "/Pub")
public class Pub extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doPost(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/plain;charset=utf-8");

        String kook = httpServletRequest.getParameter("kook");
        String lv = httpServletRequest.getParameter("lv");
        String num = httpServletRequest.getParameter("num");

        InfoDao infoDao = new InfoDao();
        Info info = new Info();
        info.setKook(kook);
        info.setLv(lv);
        info.setNum(num);
        //防止给数据库null的，数据库因为偷懒设计上是允许空的
        if (!"".equals(kook) || !"".equals(lv) || !"".equals(num)) {
            if (!infoDao.Pub(info)) {
                httpServletResponse.sendError(204, "pub failed.");
            } else {
                System.out.println("发布成功");
            }
        }
    }
}
