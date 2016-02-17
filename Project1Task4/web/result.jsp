<%-- 
    Document   : result
    Created on : 2016-1-31, 13:57:35
    Author     : Jin
--%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList birdList = (ArrayList)request.getAttribute("birdList");
    String picSrc = "http://nationalzoo.si.edu" + (String)request.getAttribute("picture");
    String author = (String)request.getAttribute("author");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align:center; margin:0 auto">
        <h1>Hello World!</h1>
        <%           
            out.println("<img src=\"" + picSrc + "\"><br>");
            //out.println("http://nationalzoo.si.edu" + picSrc );
            out.println(author);           
        %>
        <br>
        <form action="searchBirdServlet" method="GET">
            
            <select id="birdType" name="birdType">
                <%
                    for(int i = 0; i < birdList.size(); i++) {
                        out.println(birdList.get(i));
                        
                    }
                %>    
            </select>
            <br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>
