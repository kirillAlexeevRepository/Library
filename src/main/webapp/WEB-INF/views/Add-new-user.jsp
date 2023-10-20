<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear Employee , Please  enter your details</h2>

<br>
<br>

<form:form action = "saveUser" modelAttribute="user">


    Email<form:input path="username" />
    <br><br>
    First Name <form:input path="firstName"/>
    <br><br>
    Last Name<form:input path="lastName"/>
    <br><br>
    Phone Number<form:input path="phoneNumber"/>
    <br><br>
    Password <form:input path = "password"/>
    <br><br>

    <input type="submit" value="OK">

</form:form>

</body>
</html>