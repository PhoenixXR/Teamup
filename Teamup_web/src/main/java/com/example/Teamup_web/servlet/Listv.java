package com.example.Teamup_web.servlet;

import com.example.Teamup_web.dao.InfoDao;
import com.example.Teamup_web.entity.Info;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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
        String str = null,s = null;
        Stack<String> stack = new Stack<>();
        List<String> dataList = new ArrayList<>();
        HttpSession session=httpServletRequest.getSession(true);
        //确定请求由android发出
        // 从请求体中获取输入流
        BufferedReader reader = httpServletRequest.getReader();
        // 读取请求体中的 JSON 字符串,因为只传识别不用JSON
        //StringBuilder jsonBody = new StringBuilder();
        String way = "";
        String line;
        while ((line = reader.readLine()) != null) {
            way = line;
        }
        //System.out.println("way:"+way+"  line:"+line);
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
                    //传给android
                    out.println(str);

                    stack.push(str);
                }
                else{break;}
            }
            //传给index
            while (!stack.empty()){
                s = stack.pop();
                dataList.add(s);
            }
            // 将数据设置到请求属性中,转发到 JSP 页面进行显示
            httpServletRequest.setAttribute("dataList", dataList);
            session.setAttribute("dataList", dataList);
            if("".equals(way)) {
                //httpServletResponse.sendRedirect("http://192.168.0.115/Teamup_web_war_exploded");
                httpServletResponse.sendRedirect("http://121.40.96.127:8080/Teamup_web_war_exploded");
            }
        }
    }
}
