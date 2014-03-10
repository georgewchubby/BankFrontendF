<%-- 
    Document   : customer-edit
    Created on : Mar 8, 2014, 7:20:10 PM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Edit</title>
    </head>
    <body>
        <h1>Customer Edit</h1>
        <hr/> 
        <form action="FrontController" method="POST">
            <table border='1'>
                <tr><td>CPR:</td><td><input type="text" name="customerCPR" value="${customer.cpr}" /></td></tr>
                <tr><td>Title:</td><td><input type="text" name="customerTitle" value="${customer.title}" /></td></tr>
                <tr><td>First Name:</td><td><input type="text" name="customerFirstName" value="${customer.firstName}" /></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="customerLastName" value="${customer.lastName}" /></td></tr>
                <tr><td>Street:</td><td><input type="text" name="customerStreet" value="${customer.street}" /></td></tr>
                <tr><td>Post Code:</td><td><input type="text" name="customerPostalCode" value="${customer.postalCode}" /></td></tr>
                <tr><td>Post District:</td><td><input type="text" name="customerPostDistrict" value="${customer.postalDistrict}" /></td></tr>
                <tr><td>Phone Number:</td><td><input type="text" name="customerPhone" value="${customer.phone}" /></td></tr>
                <tr><td>Email:</td><td><input type="text" name="customerEmail" value="${customer.email}" /></td></tr>
            </table>
            <br />
            <input type="submit" value="Save Customer" onclick="form.action = 'FrontController?command=save-customer'" />
        </form>
        <br />
        <hr/>
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>
