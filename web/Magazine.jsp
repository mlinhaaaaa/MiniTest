<%-- 
    Document   : Magazine
    Created on : Apr 11, 2024, 11:29:27 AM
    Author     : This PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add new magazine!</h1>
        <%
            Integer hitsCount = (Integer) application.getAttribute("hitCounter");
            if (hitsCount == null || hitsCount == 0) {
                // Lần truy cập đầu tiên
                out.print("Welcome to my website! ");
                hitsCount = 1;
            } else {
                // Truy cập trang web trở lại
                out.print("Welcome back to my website! ");
                hitsCount += 1;
            }
            application.setAttribute("hitCounter", hitsCount);
            out.println("Total number of visits: " + hitsCount);
        %>
        <form action="NewMagazineController" method="post">
            <br><br>
            Maz ID:
            <input type="text" name="id"><br><br>
            Title:
            <input type="text" name="title"><br><br>
            Publisher:
            <input type="text" name="publisher"><br><br>
            Price:
            <input type="text" name="price"><br><br>
            <br><br>
            <input type="submit" value="Add User">
        </form>
    </body>
</html>
