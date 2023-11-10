<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


<h3>Information for All Library Users</h3>
<br><br>
<div style="display: flex; justify-content: space-between;">
    <p>Username: ${user.username}</p>
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
<br>
<security:authorize access="hasAnyRole('USER','ADMIN')">
<input type="button" value="Books"
                onclick="window.location.href = 'book_info'">
Library Book
</security:authorize>
<br><br>
<security:authorize access="hasAnyRole('USER','ADMIN')">
    <input type="button" value="Magazines"
           onclick="window.location.href = 'magazine_info'">
    Library Magazines
</security:authorize>
<br><br>
<security:authorize access="hasRole('USER')">
    <input type="button" value="My Items"
           onclick="window.location.href = 'users_item_info'">
    Items which one i taked or requested
</security:authorize>
<br><br>
<security:authorize access = "hasRole('ADMIN')">
<input type="button" value="Performance"
       onclick="window.location.href = 'manager_info'">
Only for Managers
</security:authorize>
</body>
</html>