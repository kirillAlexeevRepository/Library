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
            <th>Status</th>
    </tr>
<c:forEach var="books" items="${allBook}">

    <c:url var="requestToTake" value="requestToTakeBook">
        <c:param name="bookId" value="${books.bookId}"/>
    </c:url>
    <tr>
        <td>${books.bookName}
        <td>${books.author}
        <td>${books.amount}
        <td>${books.bookStatus}
            <security:authorize access="hasRole('USER')" >
        <td><input type="button" value="Request to Take"
                   onclick="window.location.href = '${requestToTake}'"/>
        </security:authorize>
    </tr>
</c:forEach>
    <security:authorize access="hasRole('ADMIN')" >
    <c:url var="addNewBookButton" value="addNewBook">
    </c:url>
    <se><input type="button" value="Add new Book In Library"
               onclick="window.location.href = '${addNewBookButton}'"/>
    </security:authorize>
</table>


</body>
</html>