<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Here you Can See all Book in Library  </h3>
<br>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
<table>
    <tr>
            <th>Title of the book</th>
            <th>Book's creator </th>
            <th>Total</th>
            <th>Status</th>
    </tr>
<c:forEach var="books" items="${allBook}">

    <c:url var="requestToTake" value="requestToTakeBook">
        <c:param name="bookId" value="${books.bookId}"/>
    </c:url>
    <c:url var="addMore" value="addMore">
        <c:param name="bookId" value="${books.bookId}"/>
    </c:url>
    <c:url var="delBook" value="delBook">
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
        <security:authorize access="hasRole('ADMIN')" >
        <td><input type="button" value="add"
                   onclick="window.location.href = '${addMore}'"/>
            </security:authorize>
        <security:authorize access="hasRole('ADMIN')" >
        <td><input type="button" value="del"
                   onclick="window.location.href = '${delBook}'"/>
            </security:authorize>
    </tr>
</c:forEach>
    <security:authorize access="hasRole('ADMIN')" >
    <c:url var="addNewBookButton" value="addNewBook">
    </c:url>
    <td><input type="button" value="Add new Book In Library"
               onclick="window.location.href = '${addNewBookButton}'"/>
    </security:authorize>
</table>


</body>
</html>