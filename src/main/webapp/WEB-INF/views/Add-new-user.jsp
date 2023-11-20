<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>

<h2> Please  enter new user details</h2>
<br>
<br>

<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="goBack()">Back
    </button>
</div>

<form:form action = "saveUser" modelAttribute="user">


    Email<form:input path="username" />
    <form:errors path="username"/>
    <br><br>
    First Name <form:input path="firstName"/>
    <form:errors path = "firstName"/>
    <br><br>
    Last Name<form:input path="lastName"/>
    <form:errors path = "lastName"/>
    <br><br>
    Phone Number<form:input path="phoneNumber"/>
    <form:errors path = "phoneNumber"/>
    <br><br>
    Password <form:input path="password" pattern="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$" title="like:-'Password123'" />
    <form:errors path ="password"/>
    <br><br>

    <input type="submit" value="OK">

</form:form>

</body>
</html>