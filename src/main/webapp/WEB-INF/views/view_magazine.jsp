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
<h3>Here you Can See all Magazines in Library  </h3>
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
            <th>Title of the Magazine</th>
            <th>Magazine creator </th>
            <th>Total</th>
            <th>Status</th>
    </tr>
<c:forEach var="magazine" items="${allMagazine}">
    <c:url var="requestToTake" value="requestToTakeMagazine">
        <c:param name="MagazineId" value="${magazine.magazineId}"/>
    </c:url>
    <c:url var="addMore" value="addMoreMagazine">
        <c:param name="magazineId" value="${magazine.magazineId}"/>
        <c:param name="amount" value="${magazine.amount}"/>
    </c:url>
    <c:url var="del" value="delOneMagazine">
        <c:param name="magazineId" value="${magazine.magazineId}"/>
    </c:url>
    <c:url var="usersWhoTake" value="UsersWithRequests">
        <c:param name="magazineId" value="${magazine.magazineId}"/>
    </c:url>
    <tr>
        <td>${magazine.magazineName}
        <td>${magazine.author}
        <td>${magazine.amount}
        <td>${magazine.status}
        <td>
            <c:if test="${not empty magazine.photoData}">
                <img style="width: 13%; height: 7%;" src="data:image/jpeg;base64,${Base64.encodeBase64String(magazine.photoData)}" alt="Book Image"/>
            </c:if>
        </td>
        <security:authorize access="hasRole('USER')" >
        <td><input type="button" value="Request to Take"
                   onclick="window.location.href = '${requestToTake}'"/>
            </security:authorize>
        <security:authorize access="hasRole('ADMIN')" >
        <td><input type="button" value="add"
                   onclick="window.location.href = '${addMore}'"/>
        <td><input type="button" value="del"
                   onclick="window.location.href = '${del}'"/>
        <td><input type="button" value="users who take" onclick="window.location.href = '${usersWhoTake}'"/></td>
    </security:authorize>
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