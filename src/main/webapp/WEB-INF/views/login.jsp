<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin-top: 50px;
        }

        .login-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px #000000;
            display: inline-block;
        }

        label {
            display: block;
            margin: 10px 0;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 15px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Login Page</h2>

    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required autofocus><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <button type="submit">Login</button>
    </form>

    <p>Don't have an account? <a href="registration">Register</a></p>
</div>

</body>
</html>
