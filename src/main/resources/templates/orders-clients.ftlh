<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your orders</title>
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
<h1>Ваши заказы</h1>

<#if orders??>
<#list orders as order>
    <hr>
    <h2>Заказ № ${order.id}</h2>
    <p>Дата создания: ${order.creationDate}</p>
    <p>Статус: ${order.status}</p>
    <table class="table table-bordered table-striped">
        <tr>
            <th>Название</th>
            <th>Автор</th>
            <th>Издатель</th>
            <th>Количество</th>
            <th>Цена</th>
        </tr>
        <#list order.items as item>
            <tr>
                <td>${item.name}</td>
                <td>${item.author.surname} ${item.author.name} ${item.author.lastname}</td>
                <td>${item.publisher.name}</td>
                <td>${item.quantity}</td>
                <td>${item.price} руб.</td>
            </tr>
        </#list>
    </table>
</#list>
</#if>


</body>
</html>