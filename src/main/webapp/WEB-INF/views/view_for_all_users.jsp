<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


<h3>Information for All employees</h3>
<br><br>
<p> Username:${user.username} </p>
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
<security:authorize access = "hasRole('ADMIN')">
<input type="button" value="Performance"
       onclick="window.location.href = 'manager_info'">
Only for Managers
</security:authorize>
</body>
</html>