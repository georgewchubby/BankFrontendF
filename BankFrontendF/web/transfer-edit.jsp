<%-- 
    Document   : transfer-edit
    Created on : 03-03-2014, 17:19:52
    Author     : David Wroblewski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <label for="accountNumber"><pre>Account Number:</label><input type="text" name="accountNumber" value="${account.number}"/></pre><br />
        <label for="amount"><pre>Amount:</label><input type="text" name="amount" value=""/></pre><br />
    <label for="targetAccountNumber"><pre>Target Account Number:</label><input type="text" name="targetAccountNumber" value=""/></pre><br />
<button>Complete Transfer</button>
</form>
<form action="FrontController" method="POST">
    <input type="hidden" name="command" value="list-accounts"/>
    <input type="hidden" name="cpr" value="${cpr}"/><br />
    <button>Abort Transfer</button>
</form>
<a href="FrontController"> Back to Main</a>
</body>
</html>