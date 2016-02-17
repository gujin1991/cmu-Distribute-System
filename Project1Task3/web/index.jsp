<%-- 
    Document   : index
    Created on : 2016-1-30, 13:59:56
    Author     : Jin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="javaScripts/comunication.js"></script>        
    </head>
    <body>
        <form id="main" action="test">
            <input type="text" id="userInput" value="">
            <button id="submit" type="submit" onclick="showHint()"> is palindrome? </button>
            <input type="text" id="result">
            
        </form> 
    </body>
    <script src="javaScripts/stopSubmit.js"></script>
</html>

