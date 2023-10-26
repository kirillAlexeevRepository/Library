<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Here you Can See all Salaries All Users </h3>
<br>
<table>
    <tr>
            <th>Username</th>
            <th>Salary</th>
    </tr>
<c:forEach var="users" items="${allUser}">
    <tr>
        <td>${users.username}
    </tr>
</c:forEach>
</table>


</body>
</html>