<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add staff</title>
</head>
<body>

<h1>Добавить работника</h1>

<form action="/user/add-staff" method="post">
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
                <input type="text" name="password">
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
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>

</body>
</html>