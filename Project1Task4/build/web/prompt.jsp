<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%
    ArrayList birdList = (ArrayList)request.getAttribute("birdList");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>You can do anything with dreams or IN dreams.</p>
        <form action="searchBirdServlet" method="GET">
            <label for="letter">Select a type of bird.</label>
            <select id="birdType" name="birdType">
                <%
                    for(int i = 0; i < birdList.size(); i++) {
                        out.println(birdList.get(i));
                    }
                %>    
            </select>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>