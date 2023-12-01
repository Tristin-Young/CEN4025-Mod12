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
<!-- Get the list of items from the database and display them in a table
     method: GET
     returns: index, name, description, isCompleted, Action-->
<table>
    <tr>
        <!-- create a table row with all header names-->
        <th>Index</th>
        <th>Name</th>
        <th>Description</th>
        <th>Is Completed</th>
        <th>Action</th>
    </tr>
    <!-- set variable items to the attribute with same name
     If there are no items, return a statement saying so
     Otherwise, for each item in the items list-->
    <%
        List<Items> items = (List<Items>) request.getAttribute("items");
        if (items == null || items.isEmpty()) {
            out.println("<tr><td colspan=\"4\">No items found</td></tr>");
        } else {
            for (Items item : items) {
    %>
    <!-- create a table row with all the item information-->
    <tr>
        <td class="item-id"><%= item.getItemId() %></td>
        <td class="item-name"><%= item.getItemName() %></td>
        <td class="item-description"><%= item.getItemDescription() %></td>
        <td class="is-completed"><%= item.getIsDone() == 1 ? "Yes" : "No" %></td>
        <td class="action">
            <!-- form to update or delete an item
                 method: POST
                 requirements: itemId-->
            <form action="update" method="POST" style="display: inline;">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="submit" value="update">
<%--                <input type="submit" value="delete">--%>
            </form>
        </td>
    </tr>
    <% }} %>
</table>
<!-- Adding in delete item button to show injection leak-->
<div class="add-item-container">
    <a href="/Assignment5P2-1.0-SNAPSHOT/delete" class="add-item-link">Delete Item</a>
</div>
</body>
</html>
