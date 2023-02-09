<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Set prices</title>
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>

<input type="button" value="Администрирование" onclick="location.href='/bookseller/administration'">
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
        <th>Установить цену</th>
    </tr>
    <#list books as book>
        <tr>
            <td>${book.name}</td>
            <td>${book.author.surname} ${book.author.name} ${book.author.lastname}</td>
            <td>${book.publisher.name}</td>
            <td>${book.quantity}</td>
            <td>${book.publicationYear}</td>
            <td>${book.price}</td>
            <td>
                <form action="/books/set-price" method="post">
                    <input hidden name="id" value=${book.id}>
                    <input type="number" step="0.01" name="price">
                    <input type="submit" value="Установить цену">
                </form>
            </td>
        </tr>
    </#list>
</table>

</body>
</html>