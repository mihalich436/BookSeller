<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book seller</title>
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>

<h1>Книготорговая база</h1>

<p><input type="button" value="Наш ассортимент" onclick="location.href='/books/'"></p>
<p><input type="button" value="Личный кабинет" onclick="location.href='/user/info'"></p>

<#if role=="ROLE_STAFF">
    <p><input type="button" value="Необработанные заказы" onclick="location.href='/orders/staff'"></p>
    <p><input type="button" value="Формируемые заказы" onclick="location.href='/orders/staff-assemble'"></p>
</#if>
<#if role=="ROLE_ADMIN">
    <p><input type="button" value="Администрирование" onclick="location.href='/bookseller/administration'"></p>
</#if>


</body>
</html>