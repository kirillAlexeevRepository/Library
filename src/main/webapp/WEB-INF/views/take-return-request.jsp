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
            <th>Username who requested</th>
            <th>Status</th>
    </tr>
        <c:forEach var="Items" items="${ItemsList}">
    <c:url var="acceptRequest" value="acceptRequest">
        <c:param name="ItemId" value="${Items.itemId}"/>
        <c:param name="ItemStatus" value="${Items.itemStatus}"/>
    </c:url>
    <c:url var="declineRequest" value="declineRequest">
        <c:param name="ItemId" value="${Items.itemId}"/>
        <c:param name="ItemStatus" value="${Items.itemStatus}"/>
    </c:url>
    <tr>
        <td>${Items.book.bookName}
        <td>${Items.user.username}
        <td>${Items.itemStatus}
        <td><input type="button" value="accept"
                   onclick="window.location.href = '${acceptRequest}'"/>
        <td><input type="button" value="decline"
                   onclick="window.location.href = '${declineRequest}'"/>
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
    <c:forEach var="Items" items="${ItemsList}">
        <tr>
            <td>${Items.magazine.magazineName}
            <td>${Items.user.username}
            <td>${Items.itemStatus}
            <td><input type="button" value="accept"
                       onclick="window.location.href = '${acceptRequest}'"/>
            <td><input type="button" value="decline"
                       onclick="window.location.href = '${declineRequest}'"/>
        </tr>
    </c:forEach>
</table>
</body>
</html>