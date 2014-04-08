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
                        accountInterest: {required: true, number: true}
                    },
                    messages: {
                        accountInterest: {
                            required: "Please enter an interest value",
                            number: "The interest must be a decimal number"
                        }
                    }
                    ,
                    errorLabelContainer: "#errors", wrapper: "div"
                });
            });
        </script>
    </head>
    <body>
        <h1>Create Account</h1>
        <hr/> 
        <form action="FrontController" method="POST" id="myform">
            <table border='1'>
                <tr><td>CPR:</td><td><input type="text" name="customerCpr" value="${customer.cpr}" readonly="true" /></td></tr>
                <tr><td>Account Number:</td><td><input type="text" name="accountNumber" value="Automatically generated" readonly="true" /></td></tr>
                <tr><td>Interest:</td><td><input type="text" name="accountInterest" id="accountInterest" /></td></tr>
            </table>
            <br />
            <input type="submit" value="Create Account" onclick="form.action = 'FrontController?command=save-account'" />
            <input type="submit" value="Cancel" onclick="form.action = 'FrontController?command=cancel-create-account'" />
        </form>
        <div id="errors" class="fs2" style="border : red solid thin;"></div>
        <br />
        <hr/>
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>
