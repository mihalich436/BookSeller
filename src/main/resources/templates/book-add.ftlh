<script>
function pickAuthor(authorID){
    if(authorID==="0"){
        document.getElementById("newAuthor").removeAttribute("hidden");
    }
    else{
        document.getElementById("newAuthor").setAttribute("hidden", "hidden");
    }
}

function pickPublisher(publisherID){
    if(publisherID==="0"){
        document.getElementById("newPublisher").removeAttribute("hidden");
    }
    else{
        document.getElementById("newPublisher").setAttribute("hidden", "hidden");
    }
}
</script>

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

<h1>Новая книга</h1>

<input type="button" value="Весь ассортимент" onclick="location.href='/books/'">

<form action="/books/add" method="post">
    <table>
        <tr>
            <td>
                <label>Название</label>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>
                <label>Автор</label>
                <select name="authorID" onchange="pickAuthor(value)">
                    <option value="0">Ввод</option>
                    <#list authors as author>
                        <option value=${author.id}>${author.surname} ${author.name} ${author.lastname}</option>
                    </#list>
                </select>
                <div id="newAuthor">
                    <label>Автор</label>
                    <input hidden name="author.id" value=0>
                    <label>Фамилия</label>
                    <input type="text" name="author.surname">
                    <label>Имя</label>
                    <input type="text" name="author.name">
                    <label>Отчество</label>
                    <input type="text" name="author.lastname">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <label>Издательство</label>
                <select name="publisherID" onchange="pickPublisher(value)">
                    <option value="0">Новый издатель</option>
                    <#list publishers as publisher>
                        <option value=${publisher.id}>${publisher.name}</option>
                    </#list>
                </select>
                <div id="newPublisher">
                    <label>Название</label>
                    <input type="text" name="publisher.name">
                    <label>Телефон</label>
                    <input type="text" name="publisher.phoneNumber">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <label>Количество на складе</label>
                <input type="number" name="quantity">
            </td>
        </tr>
        <tr>
            <td>
                <label>Год издания</label>
                <input type="number" name="publicationYear">
            </td>
        </tr>
        <tr>
            <td>
                <label>Цена</label>
                <input type="number" name="price">
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