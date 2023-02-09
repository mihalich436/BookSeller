<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
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
<h1>Профиль</h1>
<table>
    <tr>
        <th>Фамилия</th>
        <td>${userinfo.surname}</td>
    </tr>
    <tr>
        <th>Имя</th>
        <td>${userinfo.name}</td>
    </tr>
    <tr>
        <th>Отчество</th>
        <td>${userinfo.lastname}</td>
    </tr>
    <tr>
        <th>Телефон</th>
        <td>${userinfo.phoneNumber}</td>
    </tr>
</table>
<p>
<input type="button" value="Заказы" onclick="location.href='/orders/'">
</p>
</body>
</html>