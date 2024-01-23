<%@ page import="com.example.model.Todo" %>
<%@ page import="com.example.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit My Todo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="../css/todoCss.css">
    <script src="../js/todoJS.js"></script>
</head>
<body>
<%
    Todo myTodo = (Todo) request.getAttribute("myTodo");
    User user = (User) session.getAttribute("user");
%>
<div class="container m-5 p-2 rounded mx-auto bg-light shadow">
    <!-- App title section -->
    <div class="row m-1 p-4">
        <div class="col">
            <div class="p-1 h1 text-primary text-center mx-auto display-inline-block">
                <i class="fa fa-check bg-primary text-white rounded p-2"></i>
                <u>My Todo-s</u>
            </div>
        </div>
    </div>
    <!-- Create todo section -->
    <%if (myTodo != null && myTodo.getUser().getId() == user.getId()) {%>
    <div class="row m-1 p-3">
        <div class="col col-11 mx-auto">
            <form action="editTodo" method="post">
                <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                    <div class="col">
                        <input type="hidden" name="id" value="<%=myTodo.getId()%>">
                        <input type="hidden" name="createdDate" value="<%=myTodo.getCreatedDate()%>">
                        <input class="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                               type="text" value="<%=myTodo.getTitle()%>" name="title">
                    </div>
                    <div class="col-auto m-0 px-2 d-flex align-items-center">
                        <label class="text-secondary my-2 p-0 px-1 view-opt-label due-date-label d-none">Due date not
                            set</label>
                        <input type="date" style="color: blue" name="finishDate" value="<%=myTodo.getFinishDate()%>">
                    </div>
                    <div class="col-auto px-0 mx-0 mr-2">
                        <label for="status">Status: </label>
                        <select name="status" id="status">
                            <option value="NEW">NEW</option>
                            <option value="DONE">DONE</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%}%>
    <div class="p-2 mx-4 border-black-25 border-bottom"></div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootlint/1.1.0/bootlint.min.js"></script>
</body>
</html>
