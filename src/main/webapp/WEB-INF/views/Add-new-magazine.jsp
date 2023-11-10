<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear Admin , Please  enter magazines details</h2>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>

<br>
<br>

<form:form action = "saveMagazine" modelAttribute="magazine">
    Title of the magazines <form:input path="magazineName" />
    <form:errors path="magazineName"/>
    <br><br>
    Magazines creator <form:input path="author"/>
    <form:errors path="author"/>
    <br><br>
    Total <form:input path="amount"/>
    <form:errors path="amount"/>
    <br><br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>