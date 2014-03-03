<%-- 
    Document   : account-detail
    Created on : Mar 3, 2014, 9:16:51 PM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Details</title>
    </head>
    <body>
        <h1>Account list</h1>
        <hr/>
        ${message} ${accountid.number}</br>
        Type: ${accountdetail.type}</br>
        Interest ${accountdetail.interest}</br>
        <table border='1'>
            There are ${accountdetail.transfers.size()} transfers
            <tr><td>Date</td><td>Amount</td><td>Account Number</td></tr>
            <c:forEach var="transfers" items="${transfers}">
                <tr><td>${transfers.date}</td><td>${transfers.amount}</td><td>${transfers.accountNumber}</td></tr>
                    </c:forEach>
        </table>
        <hr/> 
        <a href="FrontController?command=back">Back to main page</a>
    </body>
</html>
