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
<input type="button" value="Главная страница" onclick="location.href='/bookseller/main'">
<#if authenticated==false>
    <input type="button" value="Войти" onclick="location.href='/login'">
    <input type="button" value="Зарегистрироваться" onclick="location.href='/user/register'">

<#else >
    <input type="button" value="Выйти" onclick="location.href='/logout'">
    <input type="button" value="Корзина" onclick="location.href='cart'">
</#if>
<br>


<h1>Ассортимент</h1>
<br>
<table class="table table-bordered table-striped">
    <tr>
        <th>Название</th>
        <th>Автор</th>
        <th>Издатель</th>
        <th>Количество</th>
        <th>Год издания</th>
        <th>Цена</th>
    </tr>
    <#list books as book>
        <tr>
            <td>${book.name}</td>
            <td>${book.author.surname} ${book.author.name} ${book.author.lastname}</td>
            <td>${book.publisher.name}</td>
            <td>${book.quantity}</td>
            <td>${book.publicationYear?int}</td>
            <td>${book.price} руб.</td>
            <#if authenticated==true>

                <td>
                    <form action="to-cart" method="post">
                        <input hidden name="bookID" value=${book.id}>
                        <input type="number" name="quantity">
                        <input type="submit" value="В корзину">
                    </form>
                </td>
            </#if>
        </tr>
    </#list>
</table>

</body>
</html>