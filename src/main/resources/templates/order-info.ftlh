<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>
<input type="button" value="Главная страница" onclick="location.href='/bookseller/main'">
<input type="button" value="Назад" onclick="location.href='/orders/find-form'">

<h1>Заказ № ${order.id}</h1>
<table>
    <tr>
        <th>Заказчик</th>
        <td>${order.clientID}</td>
    </tr>
    <tr>
        <th>Статус заказа</th>
        <td>${order.status}</td>
    </tr>
</table>

<#if order.statusID == 3>
    <input type="button" value="Выдать" onclick="location.href='/orders/set-status/${order.id}/4'">
</#if>

</body>
</html>