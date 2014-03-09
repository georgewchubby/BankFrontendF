<%-- 
    Document   : account-edit
    Created on : Mar 9, 2014, 9:21:43 PM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <hr/> 
        <form action="FrontController" method="POST">
            <table border='1'>
                <tr><td>CPR:</td><td><input type="text" name="customerCpr" value="${customer.cpr}" readonly="true" /></td></tr>
                <tr><td>Account Number:</td><td><input type="text" name="accountNumber" value="Automatically generated" readonly="true" /></td></tr>
                <tr><td>Interest:</td><td><input type="text" name="accountInterest" /></td></tr>
            </table>
            <br />
            <input type="submit" value="Create Account" onclick="form.action = 'FrontController?command=save-account'" />
            <input type="submit" value="Cancel" onclick="form.action = 'FrontController?command=cancel-create-account'" />
        </form>
        <br />
        <hr/>
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>
