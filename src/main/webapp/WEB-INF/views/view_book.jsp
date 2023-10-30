<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Here you Can See all Book in Library  </h3>
<br>
<table>
    <tr>
            <th>Title of the book</th>
            <th>Book's creator </th>
            <th>Total</th>
            <th>Status</th>
    </tr>
<c:forEach var="books" items="${allBook}">
    <tr>
        <td>${books.bookName}
        <td>${books.author}
        <td>${books.amount}
        <td>${books.bookStatus}
    </tr>
</c:forEach>
    <c:url var="addNewBookButton" value="addNewBook">
    </c:url>
    <td><input type="button" value="Add new Book In Library"
               onclick="window.location.href = '${addNewBookButton}'"/>
</table>


</body>
</html>