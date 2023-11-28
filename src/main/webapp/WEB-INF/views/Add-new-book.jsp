<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Page</title>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<body>
<h2>Dear Admin , Please  enter book details</h2>
<br>
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
<form:form action="saveBook" method="post" modelAttribute="book" enctype="multipart/form-data"  >
    Title of the book <form:input path="bookName" />
    <form:errors path = "bookName"/>
    <br><br>
    Book's creator <form:input path="author"/>
    <form:errors path = "author"/>
    <br><br>
    Total <form:input path="amount" type="number" max="20"/>
    <form:errors path = "amount"/>
    <br><br>
    Book Image: <input type="file" name="imageFile" accept="image/*"/>
    <br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="OK">
</form:form>
</body>
</html>