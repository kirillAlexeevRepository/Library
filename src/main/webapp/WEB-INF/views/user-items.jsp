<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h2>Here you Can See yours items  </h2>
<br>
<h2>Books</h2>
<table>
    <tr>
            <th>Title of the book</th>
            <th>Book's creator </th>
            <th>Total</th>
            <th>Status</th>
    </tr>
        <c:forEach var="books" items="${bookList}">
    <c:url var="requestToReturnBook" value="requestToReturnBook">
        <c:param name="bookId" value="${books.bookId}"/>
    </c:url>
    <tr>
        <td>${books.bookName}
        <td>${books.author}
        <td>${books.amount}
        <td>${books.bookStatus}
        <td><input type="button" value="Request to Return"
                   onclick="window.location.href = '${requestToReturnBook}'"/>
    </tr>
        </c:forEach>
</table>
<br>
<br>
<h2>Magazines</h2>
<table>
    <tr>
        <th>Title of the Magazines</th>
        <th>Magazines creator </th>
        <th>Total</th>
        <th>Status</th>
    </tr>
        <c:forEach var="magazines" items="${magazineList}">

        <c:url var="requestToReturnMagazine" value="requestToReturnMagazine">
            <c:param name="magazineId" value="${magazines.magazineId}"/>
        </c:url>
        <tr>
            <td>${magazines.magazineName}
            <td>${magazines.author}
            <td>${magazines.amount}
            <td>${magazines.status}
            <td><input type="button" value="Request to Return"
                       onclick="window.location.href = '${requestToReturnMagazine}'"/>
        </tr>
        </c:forEach>
</table>
</body>
</html>