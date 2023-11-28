<%@ page import="java.util.List" %>
<%@ page import="entity.Items" %>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>To-Do List</h1>
<div class="add-item-container">
    <a href="add.jsp" class="add-item-link">Add New Item</a>
</div>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Is Completed</th>
        <th>Action</th>
    </tr>
    <%
        List<Items> items = (List<Items>) request.getAttribute("items");
        if (items == null || items.isEmpty()) {
            out.println("<tr><td colspan=\"4\">No items found</td></tr>");
        } else {
            for (Items item : items) {
    %>
    <tr>
        <td class="item-name"><%= item.getItemName() %></td>
        <td class="item-description"><%= item.getItemDescription() %></td>
        <td class="is-completed"><%= item.getIsDone() == 1 ? "Yes" : "No" %></td>
        <td class="action">
            <form action="todo" method="POST" style="display: inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="submit" value="Delete">
            </form>
            <form action="todo" method="POST" style="display: inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <% }} %>
</table>
</body>
</html>
