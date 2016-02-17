<%-- 
    Document   : calculator
    Created on : 2016-1-29, 17:07:47
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
            <input type="text" id="first" value="">
            <select id="operation">  
                <option value ="1">add</option>  
                <option value ="2">multiply</option>  
                <option value="3">relativelyPrime</option>  
                <option value="4">mod</option>
                <option value="5">modInverse</option>
                <option value="6">power</option>
            </select>
            <input type="text" id="second" value="">
            <button id="submit" type="submit" onclick="showHint()"> = </button>
            <input type="text" id="result">
            
        </form> 
    </body>
    <script src="javaScripts/stopSubmit.js"></script>
</html>
