<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/registerCss.css">
    <title>Register</title>
</head>
<body>
<div class="register-container">
    <h2>Register</h2>
    <%
        String msg = (String) request.getSession().getAttribute("msg");
        if (msg != null) { %>
    <p style="color:red; text-align: center"><%=msg%>
    </p>
    <% }
        request.getSession().removeAttribute("msg");
    %>
    <form action="register" method="post">
        <div class="input-container">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="input-container">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="input-container">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit">Register</button>
    </form>
    <p class="txt">Have Account ? <a href="login">Login Here</a></p>
</div>
</body>
</html>
