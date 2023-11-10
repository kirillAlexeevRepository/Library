<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear Admin , Please  enter book details</h2>
<br>
<div style="display: flex; justify-content: space-between;">
    <button style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;"
            onclick="window.location.href = 'login'">
        Logout
    </button>
</div>
<br>

<form:form action = "saveBook" modelAttribute="book">

    Title of the book <form:input path="bookName" />
    <form:errors path = "bookName"/>
    <br><br>
    Book's creator <form:input path="author"/>
    <form:errors path = "author"/>
    <br><br>
    Total <form:input path="amount"/>
    <form:errors path = "amount"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>