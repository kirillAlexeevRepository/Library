<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>

<body>

<h2>Dear Employee , Please  enter your details</h2>

<br>
<br>

<form:form action = "saveBook" modelAttribute="book">


    Title of the book <form:input path="bookName" />
    <br><br>
    Book's creator <form:input path="author"/>
    <br><br>
    Total <form:input path="amount"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>