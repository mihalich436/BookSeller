<script>
    function togglePasswordVisibility(){
        if(document.getElementById("password").getAttribute("type") === "password"){
            document.getElementById("password").setAttribute("type", "text");
            document.getElementById("togglePass").setAttribute("value", "Скрыть");
        }
        else{
            document.getElementById("password").setAttribute("type", "password");
            document.getElementById("togglePass").setAttribute("value", "Показать");
        }
    }
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>

<h1>Регистрация</h1>

<form action="/user/register" method="post">
    <input hidden name="id" value="0">
    <table>
        <tr>
            <td>
                <label>Логин</label>
                <input type="text" name="username">
            </td>
        </tr>
        <tr>
            <td>
                <label>Пароль</label>
                <input type="password" name="password" id="password">
                <input type="button" value="Показать" id="togglePass" onclick="togglePasswordVisibility()">
            </td>
        </tr>
        <tr>
            <td>
                <label>Фамилия</label>
                <input type="text" name="userInfo.surname">
            </td>
        </tr>
        <tr>
            <td>
                <label>Имя</label>
                <input type="text" name="userInfo.name">
            </td>
        </tr>
        <tr>
            <td>
                <label>Отчество</label>
                <input type="text" name="userInfo.lastname">
            </td>
        </tr>
        <tr>
            <td>
                <label>Телефон</label>
                <input type="text" name="userInfo.phoneNumber">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Зарегистрироваться">
            </td>
        </tr>
    </table>
</form>

</body>
</html>