<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
      label {display: block;width: 8em;text-align: left;float: left;}
      #login {margin-left: auto;margin-right:auto ;margin-top :2em;width:20em;}
    </style>
    <title>Login Page</title>
  </head>
  <body>
    <fieldset id='login'>
      <legend>Login</legend>
      <form method="POST" action="j_security_check">
        <div><label for="j_username">User name</label> <input type="text" name="j_username" id="j_username"></div>
        <div><label for="j_password"> Password</label> <input type="password" name="j_password" id="j_password"></div>
          <input type='submit' value='login'/>
      </form>
      <p style='color:red'>${loginerror}</p>
    </fieldset>
  </body>
</html>
