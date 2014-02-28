<%-- 
    Document   : main
    Created on : Feb 28, 2014, 11:48:59 AM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank main page</title>
    </head>
    <body>
        <h1>Bank main page ${message}</h1>
        <hr/>
        <ul>
            <li><a href="FrontController?command=list-customers">List customers</a></li>
        </ul>
    </body>
</html>