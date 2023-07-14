<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.Teamup_web.servlet.Listv" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<%--    <link rel="stylesheet" href="styles.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/styles.css"/>">--%>
    <title>Teamup</title>
</head>
<body>
<script>
    var refreshButton = document.getElementById('refreshButton');
    refreshButton.addEventListener('click', function() {
        refreshJspPage();
        refreshServletPage();
    });

    function refreshServletPage() {
        window.location.href = 'com/example/Teamup_web/servlet/Listv';
    }
    function refreshJspPage() {
        // 使用 JavaScript 或者 meta 标签等方式刷新 JSP 页面
        location.reload();
    }
</script>


<h1 class="page-title" style="color: #666666;
    background-color: cornsilk;
    font-size: 30px;
    font-weight: bold;
    text-align: center;">
    <%= "组队信息" %>
</h1>
<form style="text-align: center;

}">
    <!-- 按钮调用 JavaScript 函数 -->
    <button id="refreshButton" onclick="reload()" style="font-size: 16px;
    color: white;background-color: limegreen;
    border: 1px solid aquamarine;
    border-radius: 5px;
    width: 60px;height: 40px;
">
        刷新</button>
</form>

<%--<h1>${sessionScope.dataList}</h1>--%>


<table id="tBody" style="background-color: #f2f2f2; color: #333; font-size: 25px;text-align: center;
margin-left: auto; margin-right: auto;" >

<%
    List list=(List)session.getAttribute("dataList");
    String str = null;
    for(int i=0;i<list.size()&&list.size()>0;i++){
        str= list.get(i).toString();
        //System.out.println(ms);
        int t,kook,lv;
        String time = "100";
        t = str.indexOf('|');
        kook = str.indexOf('|',t+1);
        lv = str.indexOf('|',kook+1);
        if(t>0) {
            time = str.substring(0, t)+"s";
        }
        //>15min的显示大于
        if(time.length()>4){
            time = "超过15分钟";
        }
        //System.out.println(time);
        str = "已经发布:"+time+
                "|ID:"+str.substring(t+1,kook)+
                "|"+str.substring(kook+1,lv)+
                "⭐|"+str.substring(lv+1);
    %>
<tr >
    <td><%= str %></td>
</tr>
<%
    }
%>
</table>

</body>
</html>