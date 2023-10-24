<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


<h3>Information for All employees</h3>
<br><br>
<p> Username:${user.username} </p>
<security:authorize access="hasRole('ADMIN')">
<input type="button" value="Salary"
                onclick="window.location.href = 'hr_info'">
Only for HR staff
</security:authorize>
<br><br>
<security:authorize access = "hasRole('ADMIN')">
<input type="button" value="Performance"
       onclick="window.location.href = 'manager_info'">
Only for Managers
</security:authorize>
</body>
</html>