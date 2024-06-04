
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Ассортимент библиотеки</title>
</head>
<style>
  th, td{
    border: 2px black solid;
  }
</style>
<body>
<table>
  <tr><th>Название</th><th>Автор</th><th></th></tr>
  <c:forEach var="book" items="${books}">
    <tr><td>${book.title}</td>
      <td>${book.author}</td>
  <td>
    <a href='<c:url value="/update?id=${book.id}" />'>Изменить</a> |
    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
      <input type="hidden" name="id" value="${book.id}">
      <input type="submit" value="Delete">
    </form>
  </td></tr>
  </c:forEach>
</table>
<h3><a href="/">Вернуться на главную</a></h3>
</body>
</html>
