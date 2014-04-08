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
        <style type="text/css">
            #myform label.error {
                color:red; width:auto; font-size: small;
                float : right; display: block;
            }
            #myform input.error {
                border:1px solid red;
            }
        </style>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
        <script>
            $(document).ready(function() {
                // validate the comment form when it is submitted
                $("#myform").validate({
                    rules: {
                        amount: {required: true, number: true},
                        targetAccountNumber: {required: true}
                        
                    },
                    messages: {
                        amount: {
                            required: "Please enter an amount to be transferred",
                            number: "You must enter a valid number"
                        },
                        targetAccountNumber: {
                            required: "Please enter a account number"
                        }
                    }
                    ,
                    errorLabelContainer: "#errors", wrapper: "div"
                });
            });
        </script>
    </head>
    <body>
        <h1>Transfer Page</h1>
        <hr/> 
        <p>Fill out the boxes below with valid information</p>
        <form action="FrontController" method="POST" id="myform">
            Account Number:
            <select name="accountNumber">
                <c:forEach var="accounts" items="${accounts}">
                    <option>${accounts.number}</option>
                </c:forEach>
            </select>
            <br />
            <br />
            Amount:<input type="text" name="amount" id="amount" /><br />
            <br />
            Target Account Number:<input type="text" name="targetAccountNumber" id="targetAccountNumber" /><br />
            <br />
            <input type="submit" value="Complete transfer" onclick="form.action = 'FrontController?command=transfer-amount'" />
            <input type="submit" value="Cancel transfer" onclick="form.action = 'FrontController?command=cancel-transfer'" /> <br />
        </form>
        <div id="errors" class="fs2" style="border : red solid thin;"></div>
        <br />
        <hr />
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>