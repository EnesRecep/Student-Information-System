<%-- 
    Document   : loginCheck
    Created on : Jul 19, 2018, 11:12:54 AM
    Author     : omer
--%>
<%@page import="tr.com.argela.obs.core.models.Exam"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.List"%>
<%@page import="tr.com.argela.obs.core.models.Student"%>
<%@page import="tr.com.argela.obs.core.remote.MainService"%>
<%@page import="tr.com.argela.obs.core.app.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        MainService mainservice =  new MainService();
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
       
        if(username.length()==4 && mainservice.studentLogin(username, password))
            {
            session.setAttribute("username",username);
            response.sendRedirect("index.jsp");
            }
        else
            response.sendRedirect("Error.jsp");
        %>
    </body>
</html>




