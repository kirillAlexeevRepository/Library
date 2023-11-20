<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>
<h3>Here you Can See all Book in Library  </h3>
<br>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="goBack()">Back
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
            <td>${books.bookName}</td>
            <td>${books.author}</td>
            <td>${books.amount}</td>
            <td>${books.bookStatus}</td>
            <td>
                <c:if test="${not empty books.photoData}">
                    <img style="width: 13%; height: 7%;" src="data:image/jpeg;base64,${Base64.encodeBase64String(books.photoData)}" alt="Book Image"/>
                </c:if>
            </td>
            <security:authorize access="hasRole('USER')">
                <td><input type="button" value="Request to Take" onclick="window.location.href = '${requestToTake}'"/></td>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <td><input type="button" value="add" onclick="window.location.href = '${addMore}'"/></td>
                <td><input type="button" value="del" onclick="window.location.href = '${delBook}'"/></td>
            </security:authorize>
        </tr>
    </c:forEach>
    <security:authorize access="hasRole('ADMIN')">
        <c:url var="addNewBookButton" value="addNewBook">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </c:url>
        <td><input type="button" value="Add new Book In Library" onclick="window.location.href = '${addNewBookButton}'"/></td>
    </security:authorize>
</table>
</body>
</html>
