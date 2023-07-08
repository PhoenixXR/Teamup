package com.example.Teamup_web.servlet;

import com.example.Teamup_web.dao.InfoDao;
import com.example.Teamup_web.entity.Info;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "listv", value = "/listv")
public class Listv extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doPost(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        InfoDao infoDao = new InfoDao();
        Queue <Info> queue = infoDao.listv();
        PrintWriter out = httpServletResponse.getWriter();
        String str = null;

        //下面那个一直不变现在想到怎么改
        if("".equals(queue.peek()))
        {
            httpServletResponse.sendError(204,"listv failed.");
        }
        else{
            while (!"".equals(queue.peek())) {

                Info i = queue.poll();
                if (i != null) {
                    str = i.getAll();
                    out.println(str);
                }
                else{break;}
            }
        }
    }
}
