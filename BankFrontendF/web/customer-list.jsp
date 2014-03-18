<%-- 
    Document   : customer-list
    Created on : Feb 28, 2014, 12:11:43 PM
    Author     : joachim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer list</title>
    </head>
    <body>
        <h1>Customer list</h1>
        <hr/> 
        <table border='1'>     
            <c:forEach var="customers" items="${customers}">
                <tr>
                    <td><a href="FrontController?custid=${customers.cpr}&command=list-accounts">${customers.cpr}</a></td>
                    <td>${customers.firstName} ${customers.lastName}</td>
                    <td><a href="FrontController?custid=${customers.cpr}&command=change-customer">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
        <hr/> 
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>
