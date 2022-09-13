<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
    </title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<div>

    <% if (user == null) { %>
    <div style="float:right;"><a href="/login">Login</a> | <a href="/register">Register</a></div>
    <% } else { %>

    <div> Welcome <%=user.getName()%> | <a href="/items">All Items</a> | <a href="/items/add">Add Item</a> | <a href="/logout">Logout</a></div>
    <%}%>

    <div>
        <ul style="overflow:hidden">
            <li style="display: inline; margin-left:40px;"><a href="/home">Home</a></li>
            <% for (Category category : categories) { %>
            <li style="display: inline; margin-left:20px;"><a
                    href="/home?category_id=<%=category.getId()%>"><%=category.getName()%>
            </a>
            </li>
            <% } %>
        </ul>
    </div>

    <div>
        <% for (Item item : items) { %>
            <div style="width: 105px; float: left">
                <div>
                    <% if (item.getProfilePic() == null || item.getProfilePic().length() == 0) {%>
                    <img src="/image/defaultProfilePic.PNG" width="100"/>
                    <% } else { %>
                    <img src="/getImage?profilePic=<%=item.getProfilePic()%>" width="100"/>
                    <% }%>
                </div>
                <div>
                    <span><%=item.getTitle()%> | <%=item.getPrice()%> </span>
                </div>
            </div>
        <% }%>
    </div>
</div>

</body>
</html>