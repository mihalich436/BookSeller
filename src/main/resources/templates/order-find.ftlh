<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find Order</title>
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
<h1>Поиск заказа</h1>
<p>
    Номер заказа:
</p>

<form action="/orders/find" method="post">
    <input type="text" name="id">
    <input type="submit" value="Найти">
</form>

</body>
</html>