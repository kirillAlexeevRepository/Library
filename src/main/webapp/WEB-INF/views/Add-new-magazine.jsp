<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>

<h2>Dear Admin , Please  enter magazines details</h2>
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

<br>
<br>

<form:form action = "saveMagazine" method="post" modelAttribute="magazine" enctype="multipart/form-data">
    Title of the magazines <form:input path="magazineName" />
    <form:errors path="magazineName"/>
    <br><br>
    Magazines creator <form:input path="author"/>
    <form:errors path="author"/>
    <br><br>
    Total <form:input path="amount" type="number" max="20"/>
    <form:errors path="amount"/>
    <br><br>
    Book Image: <input type="file" name="imageFile" accept="image/*"/>
    <br><br>
    <input type="submit" value="OK">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form:form>
</body>
</html>