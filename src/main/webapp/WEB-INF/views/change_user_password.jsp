<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear manager , Please  enter new password </h2>

<br>
<br>

<form:form action = "putPassword" modelAttribute="user">

    <form:hidden path="username"/>
    <form:hidden path="firstName"/>
    <form:hidden path="lastName"/>
    <form:hidden path="phoneNumber"/>

    Write new Password <form:input path="password" pattern="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$" title="like:-'Password123' " />
    <form:errors path ="password"/>
    <input type="submit" value="OK">

</form:form>

</body>
</html>