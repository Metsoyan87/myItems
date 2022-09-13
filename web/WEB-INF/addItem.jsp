<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");

%>
PLease input Items
<form action="/items/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title"><br>
    <input type="number" name="price" placeholder="please input price"><br>
    <select name="category_id">
        <% for (Category category : categoryList) { %>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% } %>
    </select>

    <input type="hidden" name="userId" value="<%=user.getName()%>"><br>
    <h5>Profile Picture</h5>
    <input type="file" name="profilePic"><br>
    <input type="submit" value="add">
</form>
</body>
</html>