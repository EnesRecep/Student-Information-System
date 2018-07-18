<%-- 
    Document   : showexamInfo
    Created on : Jul 18, 2018, 1:58:33 PM
    Author     : omer
--%>

<%@page import="tr.com.argela.obs.core.models.Exam"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.List"%>
<%@page import="tr.com.argela.obs.core.models.Student"%>
<%@page import="tr.com.argela.obs.core.models.Teacher"%>
<%@page import="tr.com.argela.obs.core.models.Lecture"%>
<%@page import="tr.com.argela.obs.core.remote.MainService"%>
<%@page import="tr.com.argela.obs.core.app.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    MainService mainService = Application.getApp().getMainService();
    List<Exam> exams = mainService.showExamInfo("1000");
    
    

    ResourceBundle label = ResourceBundle.getBundle("label.label", new Locale("tr", "TR"));

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ogrenci Sınavlarını Görüntüle</title>
    </head>
    <body>
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>NAME</th>
                  <th>SEMESTER</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>ID</th>
                  <th>NAME</th>
                  <th>SEMESTER</th>
                </tr>

              </tfoot>
              <tbody>
               
              </tbody>
            </table>
    </body>
</html>
