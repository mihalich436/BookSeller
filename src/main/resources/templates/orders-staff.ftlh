<script>
    function unfold(id){
        if(document.getElementById("orderDescription" + id).hidden === true){
            document.getElementById("orderDescription" + id).hidden=false;
            document.getElementById("unfoldButton" + id).value="Скрыть";
        }
        else{
            document.getElementById("orderDescription" + id).hidden=true;
            document.getElementById("unfoldButton" + id).value="Подробности";
        }
    }
</script>

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
<input type="button" value="Формируемые заказы" onclick="location.href='/orders/staff-assemble'">
<h1>Необработанные заказы</h1>

<#if orders??>
<#list orders as order>
    <hr>
    <table class="table table-bordered table-striped">
        <tr>
            <th>Номер</th>
            <th>Дата создания</th>
            <th>Статус</th>
            <th>Позиций</th>
            <th>Действия</th>
        </tr>
        <tr>
            <td>${order.id}</td>
            <td>${order.creationDate}</td>
            <td>${order.status}</td>
            <td>${order.items?size}</td>
            <td>
                <input type="button" value="Подробности" onclick="unfold(${order.id})" id="unfoldButton${order.id}">
                <input type="button" value="Собрать" onclick="location.href='/orders/assemble/${order.id}'">
            </td>
        </tr>
    </table>
    <table class="table table-bordered table-striped" id="orderDescription${order.id}" hidden>
        <tr>
            <th>Название</th>
            <th>Автор</th>
            <th>Издатель</th>
            <th>Количество</th>
            <th>Цена</th>
            <th>Собрано</th>
        </tr>
        <#list order.items as item>
            <tr>
                <td>${item.name}</td>
                <td>${item.author.surname} ${item.author.name} ${item.author.lastname}</td>
                <td>${item.publisher.name}</td>
                <td>${item.quantity}</td>
                <td>${item.price} руб.</td>
                <td><#if item.assembled??>${item.assembled}<#else>0</#if></td>
            </tr>
        </#list>
    </table>
</#list>
</#if>

</body>
</html>