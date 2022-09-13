<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Add User</title>
</head>
<body>

<%
    String msg = (String) request.getAttribute("msg");
%>

<% if (msg != null) {%>
<p style="color: maroon"><%=msg%>
</p>
<%}%>


<center><h1> Register </h1></center>

<form class="form-horizontal" action="/users/add" method="post">
    <fieldset>
        <div id="legend">

        </div>
        <div class="control-group">
            <!-- Username -->
            <label class="control-label" for="name">Name</label>
            <div class="controls">
                <input type="text" id="name" name="name" placeholder="" class="input-xlarge">
            </div>
        </div>
        <div class="control-group">
            <!-- Username -->
            <label class="control-label" for="surname">SurName</label>
            <div class="controls">
                <input type="text" id="surname" name="surname" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- E-mail -->
            <label class="control-label" for="email">E-mail</label>
            <div class="controls">
                <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Password-->
            <label class="control-label" for="password">Password</label>
            <div class="controls">
                <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
            </div>
        </div>
        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button class="btn btn-success">Register</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
