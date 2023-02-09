<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<#if param??>
<#if param.error??>
<div>
    Invalid username and password.
</div>
</#if>
<#if param.logout??>
<div>
    You have been logged out.
</div>
</#if>
</#if>
<form action="login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>