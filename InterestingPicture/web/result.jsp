<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<! >

<html>
    <head>
        <title>Interesting Picture</title>
    </head>
    <body>
        <h1>Here is an interesting picture of a <%= request.getAttribute("pictureTag")%></h1><br>
        <img src="<%= request.getAttribute("pictureURL")%>"><br><br>
         <form action="getAnInterestingPicture" method="GET">
            <label for="letter">Type another word.</label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

