<%-- 
    Document   : index
    Created on : 2016-1-27, 17:25:57
    Author     : Jin
--%>
<%@page import = "java.util.*" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ComputeHashes" method="GET">
            <input type="text" name="userinput">
            <input type="submit" name="md5" value="md5">
            <input type="submit" name="sha-1" value="sha-1">
        </form>
    </body>
</html>
