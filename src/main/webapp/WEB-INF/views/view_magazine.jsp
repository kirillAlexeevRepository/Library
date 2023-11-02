<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Here you Can See all Book in Library  </h3>
<br>
<table>
    <tr>
            <th>Title of the Magazine</th>
            <th>Magazine creator </th>
            <th>Total</th>
            <th>Status</th>
    </tr>
<c:forEach var="magazine" items="${allMagazine}">
    <tr>
        <td>${magazine.magazineName}
        <td>${magazine.author}
        <td>${magazine.amount}
        <td>${magazine.status}
    </tr>
</c:forEach>
    <security:authorize access="hasRole('ADMIN')" >
    <c:url var="addNewMagazineButton" value="addNewMagazine">
    </c:url>
    <td><input type="button" value="Add new Magazine In Library"
               onclick="window.location.href = '${addNewMagazineButton}'"/>
    </security:authorize>
</table>


</body>
</html>