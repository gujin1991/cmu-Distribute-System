<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<%@page import = "java.util.*" %>
<% LinkedList bird = (LinkedList)request.getAttribute("link"); %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <p>Jin says, Give me a keyword, and I'll give you an interesting picture. Still need improving.</p>
        <form action="getAnInterestingPicture" method="GET">
            <label for="letter">Type the word.</label>
            <input type="text" name="searchWord" value="" /><br>
            
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>

