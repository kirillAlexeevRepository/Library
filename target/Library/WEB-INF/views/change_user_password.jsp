<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear manager , Please  enter new password </h2>

<br>
<br>

<form:form action = "putPassword" modelAttribute="user">

    <form:hidden path="username"/>

    Write new Password <form:input path="password" />
<%--    Repeat Password <form:input path="repeadpassword" />--%>
    <input type="submit" value="OK">

</form:form>


</body>
</html>