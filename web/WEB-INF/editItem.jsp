

<%@ page import="model.Item" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>

<%

    Item item = (Item) request.getAttribute("item");
%>

PLease update item's data
<form action="/item/edit" method="post">

    <input type="hidden" name="itemId" value="<%=item.getId()%>">
    <input type="text" name="title" value="<%=item.getTitle()%>"> <br>
    <input type="text" name="price" value="<%=item.getPrice()%>"> <br>
    <input type="text" name="categoryId" value="<%=item.getCategory().getId()%>"> <br>
    <input type="text" name="userId" value="<%=item.getUser()%>"> <br>
    <select name="authorId">
    </select>
    <input type="submit" value="Update">
</form>
</body>
</html>
