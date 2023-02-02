<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
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

    <td>
        <% if (item.getProfilePic() == null || item.getProfilePic().length() == 0) {%>
        <img src="/image/defaultProfilePic.PNG" width="100"/>
        <% } else { %>
        <img src="/getImage?profilePic=<%=item.getProfilePic()%>" width="100"/>
        <% }%>
    </td>

    <h5>Profile Picture</h5>
    <input type="file" name="profilePic"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
