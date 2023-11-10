<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h2>Here you Can See yours items  </h2>
<br>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
<h2>Books</h2>
<table>
    <tr>
            <th>Title</th>
            <th>Creator </th>
            <th>Status</th>
    </tr>
        <c:forEach var="books" items="${bookList}">
    <c:url var="requestToReturnBook" value="requestToReturnBook">
        <c:param name="bookId" value="${books.bookId}"/>
        <c:param name="bookStatus" value="${books.bookStatus}"/>
    </c:url>
    <tr>
        <td>${books.bookName}
        <td>${books.author}
        <td>${books.bookStatus}
        <td><input type="button" value="Return back"
                   onclick="window.location.href = '${requestToReturnBook}'"/>
    </tr>
        </c:forEach>
</table>
<br>
<br>
<h2>Magazines</h2>
<table>
    <tr>
        <th>Title</th>
        <th>Creator</th>
        <th>Status</th>
    </tr>
        <c:forEach var="magazines" items="${magazineList}">

        <c:url var="requestToReturnMagazine" value="requestToReturnMagazine">
            <c:param name="magazineId" value="${magazines.magazineId}"/>
            <c:param name="magazineStatus" value="${magazines.status}"/>
        </c:url>
        <tr>
            <td>${magazines.magazineName}
            <td>${magazines.author}
            <td>${magazines.status}
            <td><input type="button" value="Return back"
                       onclick="window.location.href = '${requestToReturnMagazine}'"/>
        </tr>
        </c:forEach>
</table>
</body>
</html>