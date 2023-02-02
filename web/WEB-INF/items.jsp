<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Items</title>
</head>
<body>

<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    User user = (User) session.getAttribute("user");
%>
<p><%="barev " + user.getName()%></p>
<a href="/items/add">Add new Items</a> | <a href="/logout">Logout</a>


<table border="1">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>title</th>
        <th>price</th>
        <th>category Id</th>
        <th>user ID</th>
        <th>action</th>
        <th>action</th>
    </tr>

    <% for (Item item : items) { %>

    <tr>
        <td>
            <% if (item.getProfilePic() == null || item.getProfilePic().length() == 0) {%>
            <img src="/image/defaultProfilePic.PNG" width="100"/>
                <% } else { %>
            <img src="/getImage?profilePic=<%=item.getProfilePic()%>" width="100"/>
                <% }%>
        </td>
        <td><%=item.getId()%>
        </td>
        <td><%=item.getTitle()%>
        </td>
        <td><%=item.getPrice()%>
        </td>
        <td><%=item.getCategory().getName()%></td>

        <td><%=item.getUser().getName()%>
        </td>

        <td><a href="/items/remove?itemId=<%=item.getId()%>">Remove</a>
        <td><a href="/item/edit?itemId=<%=item.getId()%>">Edit</a>
        </td>

    </tr>

    <%}%>

</table>
</body>
</html>
