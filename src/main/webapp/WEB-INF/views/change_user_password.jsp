<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear Admin , Please  enter new password </h2>
<br>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
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