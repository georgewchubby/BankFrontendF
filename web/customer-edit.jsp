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
                        customerCPR: {required: true},
                        customerFirstName: {required: true, minlength: 2},
                        customerLastName: {required: true, minlength: 2},
                        customerEmail: {required: true, email: true}
                    },
                    messages: {
                        customerCPR: {
                            required: "Please enter a CPR-number"
                        },
                        customerFirstName: {
                            required: "Please enter you first name",
                            minlength: jQuery.format("At least {0} characters required!")
                        },
                        customerLastName: {
                            required: "Please enter your lastname",
                            minlength: jQuery.format("At least {0} characters required!")
                        },
                        customerEmail: {
                            required: "Please enter you email",
                            email: "Please enter a valid email"
                        }
                    }
                    ,
                    errorLabelContainer: "#errors", wrapper: "div"
                });
            });
        </script>
    </head>
    <body>
        <h1>Customer Edit</h1>
        <hr/> 
        <form action="FrontController" method="POST" id="myform">
            <table border='1'>
                <tr><td>CPR:</td><td><input type="text" name="customerCPR" id="customerCPR" value="${customer.cpr}" /></td></tr>
                <tr><td>Title:</td><td><input type="text" name="customerTitle" id="customerTitle" value="${customer.title}" /></td></tr>
                <tr><td>First Name:</td><td><input type="text" name="customerFirstName" id="customerFirstName" value="${customer.firstName}" /></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="customerLastName" id="customerLastName" value="${customer.lastName}" /></td></tr>
                <tr><td>Street:</td><td><input type="text" name="customerStreet" id="customerLastName" value="${customer.street}" /></td></tr>
                <tr><td>Postal Code:</td><td><input type="text" name="customerPostalCode" id="customerPostalCode" value="${customer.postalCode}" /></td></tr>
                <tr><td>Post District:</td><td><input type="text" name="customerPostalDistrict" id="customerPostalDistrict" value="${customer.postalDistrict}" /></td></tr>
                <tr><td>Phone Number:</td><td><input type="text" name="customerPhone" id="customerPhone" value="${customer.phone}" /></td></tr>
                <tr><td>Email:</td><td><input type="text" name="customerEmail" id="customerEmail" value="${customer.email}" /></td></tr>
            </table>
            <br />
            <button name="command" value="save-customer">Save Customer</button>
        </form>
        <div id="errors" class="fs2" style="border : red solid thin;"></div>
        <br />
        <hr/>
        <a href="FrontController?command=back">Back to Main</a>
    </body>
</html>
