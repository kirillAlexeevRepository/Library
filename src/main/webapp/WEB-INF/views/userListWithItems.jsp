<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>
<h3>Here you can choose one user with requests </h3>
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
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
    </tr>
    <c:forEach var="user" items="${UserListWithItems}">
    <c:url var="usersItemInfo" value="take_return_requests">
        <c:param name="username" value="${user.username}"/>
    </c:url>
        <tr>
            <td>${user.username}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phoneNumber}</td>
            <td><input type="button" value="Requests"
                       onclick="window.location.href ='${usersItemInfo}'">
    </c:forEach>
</table>
</body>
</html>
