<%-- 
    Document   : account-list
    Created on : Mar 1, 2014, 9:12:44 AM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts</title>
    </head>
    <body>
        <h1>Account list</h1>
        <hr/>
        <table border='1'> 
            ${message} for ${customer.cpr}
            <tr><td>Account Number</td><td>Account Type</td><td>Account Balance</td></tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td><a href="FrontController?accid=${account.number}&command=account-detail">${account.number}</a></td>
                    <td>${account.type}</td>
                    <td>${account.balance}</td>
                </tr>
            </c:forEach>
        </table>
        <hr/> 
        <a href="FrontController?command=back">Back to main page</a>
    </body>
</html>
