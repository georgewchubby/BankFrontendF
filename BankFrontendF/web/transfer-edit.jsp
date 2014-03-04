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
            <input type="hidden" name="command" value="transfer-amount"/>
            <label for="accountNumber">Account Number:</label>
            <select>
                <c:forEach var="accounts" items="${accounts}">
                    <option>${accounts.number}</option>
                </c:forEach>
            </select>
                        <!--<input type="text" name="accountNumber" value="${account.number}"/><br>-->
            <br>
            <label for="amount">Amount:</label><input type="text" name="amount" value=""/><br>
            <br>
            <label for="targetAccountNumber">Target Account Number:</label><input type="text" name="targetAccountNumber" value=""/><br>
            <br>
            <button>Complete Transfer</button>
        </form>
        <br>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="list-accounts"/>
            <input type="hidden" name="cpr" value="${cpr}"/>
            <button>Abort Transfer</button>
        </form>
        <br>
        <a href="FrontController"> Back to Main</a>
    </body>
</html>