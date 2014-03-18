<%-- 
    Document   : main
    Created on : Feb 28, 2014, 11:48:59 AM
    Author     : joachim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank main page</title>
    </head>
    <body>
        <h1>Bank main page ${message}</h1>
        <hr/>
        <ul>
            <li>Remote user: '${pageContext.request.remoteUser}'</li>
            <li>SuperEmployee: ${pageContext.request.isUserInRole('SuperEmployee')}</li>
            <li>Employee: ${pageContext.request.isUserInRole('Employee')}</li>
            <li>Customer: ${pageContext.request.isUserInRole('Customer')}</li>

        </ul>
        
        <ul>
            <c:if test="${pageContext.request.isUserInRole('SuperEmployee')==true}">
                <li><a href="FrontController?command=create-customer">Create customer</a></li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('Employee')==true || 
                              pageContext.request.isUserInRole('SuperEmployee')==true}">
                <li><a href="FrontController?command=list-customers">List customers</a></li>
                <a href="FrontController?command=hello">Hello</a>
                </c:if>
        </ul>
        <h3>
            <c:choose >
                <c:when test="${pageContext.request.remoteUser==null}">
                    <a href="FrontController?command=showlogin">Login</a>
                </c:when>
                <c:otherwise>
                    <a href="FrontController?command=logout">Log out</a>
                </c:otherwise>
            </c:choose>
        </h3>
    </body>
</html>