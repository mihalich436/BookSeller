<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your cart</title>
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>
<input type="button" value="Магазин" onclick="location.href='/books/'">

<h1>Корзина</h1>

<#if cartItems??>
    <table>
        <tr>
            <th>Книга</th>
            <th>Количество</th>
        </tr>
        <#list cartItems as cartItem>
            <tr>
                <td>
                    ${cartItem.bookID}
                </td>
                <td>
                    ${cartItem.quantity}
                </td>
            </tr>
        </#list>
    </table>
    <br>
    <input type="button" value="Оформить заказ" onclick="location.href='/orders/create'">
<#else>
    <p>Корзина пуста</p>
</#if>

</body>
</html>