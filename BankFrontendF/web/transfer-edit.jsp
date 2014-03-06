<%-- 
    Document   : transfer-edit
    Created on : 03-03-2014, 17:19:52
    Author     : David Wroblewski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body>
        <h1>Transfer Page</h1>
        <p>Fill out the boxes below with valid information</p>
        <form action="FrontController" method="POST">
            Account Number:
            <select name="accountNumber">
                <c:forEach var="accounts" items="${accounts}">
                    <option>${accounts.number}</option>
                </c:forEach>
            </select>
            <br />
            <br />
            Amount:<input type="text" name="amount" /><br />
            <br />
            Target Account Number:<input type="text" name="targetAccountNumber" /><br />
            <br />
            <input type="submit" value="Complete transfer" onclick="form.action = 'FrontController?command=transfer-amount'" />
            <input type="submit" value="Cancel transfer" onclick="form.action = 'FrontController?command=cancel-transfer'" /> <br />
        </form>
        <br />
        <br />
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>