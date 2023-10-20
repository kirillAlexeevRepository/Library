<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<h3>Here you Can See  Performance </h3>
<table>
    <tr>
        <th>Username</th>
    </tr>
    <c:forEach var="user" items="${allUser}">

        <c:url var="changePasswordButton" value="change_password">
            <c:param name="username" value="${user.username}"/>
        </c:url>

        <tr>
            <td>${user.username}</td>
            <td><input type="button" value="change password"
                       onclick="window.location.href = '${changePasswordButton}'"/>

        </tr>

    </c:forEach>

</table>
</body>
</html>