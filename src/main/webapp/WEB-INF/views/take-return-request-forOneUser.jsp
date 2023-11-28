<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>
<h2>Here you can see requests for One users  </h2>
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
<br>
<%-- КТо оставил запросы --%>
<br>
<h2>Books</h2>
<table>
    <tr>
            <th>Title of the book</th>
            <th>Status</th>
    </tr>
        <c:forEach var="Books" items="${bookList}">

    <c:url var="acceptRequestBook" value="acceptRequest">
        <c:param name="ItemId" value="${Books.itemId}"/>
        <c:param name="ItemStatus" value="${Books.itemStatus}"/>
        <c:param name="username" value="${Books.user.username}"/>

    </c:url>
    <c:url var="declineRequestBook" value="declineRequest">
        <c:param name="ItemId" value="${Books.itemId}"/>
        <c:param name="ItemStatus" value="${Books.itemStatus}"/>
        <c:param name="username" value="${Books.user.username}"/>
    </c:url>
    <tr>
        <td>${Books.book.bookName}
        <td>${Books.itemStatus}
        <td><input type="button" value="accept"
                   onclick="window.location.href = '${acceptRequestBook}'"/>
        <td><input type="button" value="decline"
                   onclick="window.location.href = '${declineRequestBook}'"/>
    </tr>
        </c:forEach>
</table>
<br>
<br>
<h2>Magazines</h2>
<table>
    <tr>
        <th>Title of the Magazines</th>
        <th>Username who requested</th>
        <th>Status</th>
    </tr>
    <c:forEach var="magaz" items="${magazList}">
        <c:url var="acceptRequestMagaz" value="acceptRequest">
            <c:param name="ItemId" value="${magaz.itemId}"/>
            <c:param name="ItemStatus" value="${magaz.itemStatus}"/>
            <c:param name="username" value="${magaz.user.username}"/>
        </c:url>
        <c:url var="declineRequestMagaz" value="declineRequest">
            <c:param name="ItemId" value="${magaz.itemId}"/>
            <c:param name="ItemStatus" value="${magaz.itemStatus}"/>
            <c:param name="username" value="${magaz.user.username}"/>
        </c:url>
        <tr>
            <td>${magaz.magazine.magazineName}
            <td>${magaz.itemStatus}
            <td><input type="button" value="accept"
                       onclick="window.location.href = '${acceptRequestMagaz}'"/>
            <td><input type="button" value="decline"
                       onclick="window.location.href = '${declineRequestMagaz}'"/>
        </tr>
    </c:forEach>

</table>
</body>
</html>