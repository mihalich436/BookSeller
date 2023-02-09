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

<h1>Администрирование</h1>

<input type="button" value="Дополнить ассортимент" onclick="location.href='/books/add'">
<br>
<br>
<input type="button" value="Добавить сотрудника" onclick="location.href='/user/add-staff'">
<br>
<br>
<input type="button" value="Оформить поставку" onclick="location.href='/books/supply-page'">
<br>
<br>
<input type="button" value="Изменить цены" onclick="location.href='/books/set-price-page'">

</body>
</html>