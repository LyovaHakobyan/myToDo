<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/loginCss.css">
    <title>Login</title>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <%
        String msg = (String) request.getSession().getAttribute("msg");
        if (msg != null) { %>
    <p style="color:red; text-align: center"><%=msg%>
    </p>
    <% }
        request.getSession().removeAttribute("msg");
    %>
    <form action="login" method="post">
        <label for="username">Email:</label>
        <input type="email" id="username" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Login</button>
    </form>
    <p class="txt">Not Registered ? <a href="register">Register Here</a></p>
</div>
</body>
</html>