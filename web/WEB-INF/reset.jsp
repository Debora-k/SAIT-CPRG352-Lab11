<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your address to reset your password.</p>
        <br>
        <form method="POST" action="reset">
            <label>Email Address: </label>
            <input type="text" name="email">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
