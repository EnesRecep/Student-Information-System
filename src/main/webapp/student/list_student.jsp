<%-- 
    Document   : list_student
    Created on : Jul 17, 2018, 11:25:27 AM
    Author     : enesrecep
--%>

<%@page import="tr.com.argela.obs.core.models.Exam"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.List"%>
<%@page import="tr.com.argela.obs.core.models.Student"%>
<%@page import="tr.com.argela.obs.core.remote.MainService"%>
<%@page import="tr.com.argela.obs.core.app.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MainService mainService = Application.getApp().getMainService();
    List<Student> students = mainService.getStudents();
    
    List<Exam> exams = mainService.showGivenExams("20");

    ResourceBundle label = ResourceBundle.getBundle("label.label", new Locale("tr", "TR"));

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1><%=(label.getString("STUDENT_LIST"))%></h1>

        <form>
            First Name:<br>
            <input type="text" name="firstname"><br>
        </form> 
        
        


        <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Email</td>
            </tr>

            <%
                for (Exam exam : exams) {
                   
            %>
            <tr>
                <td><%=(exam.getName())%></td>
                <td><%=(exam.getSemester())%></td>
                <td><%=(exam.getLectureId())%></td>
               

            </tr>


            <%
                }
            %>

        </table>


    </body>
</html>
