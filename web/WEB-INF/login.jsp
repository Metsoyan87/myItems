<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
</head>
<body>
<% if (request.getAttribute("message") != null) { %>
<p style="color: red"><%= request.getAttribute("message")%>
</p>
<% } %>
<center><h1> Login </h1></center>
<form class="form-horizontal" action="/login" method="post">

    <fieldset>
        <div id="legend">
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
                <button class="btn btn-success">Login</button>
            </div>
        </div>
    </fieldset>

</form>
</body>
</html>