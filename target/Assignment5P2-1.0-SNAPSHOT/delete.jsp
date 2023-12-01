<!-- Delete item in database-->

<!--imports -->
<%@ page import="java.util.List" %>
<%@ page import="entity.Items" %>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Item</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Delete Item</h1>
                <!-- Form to delete an item from the database
                method: POST
                requirements: itemId -->
                <form action="delete" method="POST" style="display: inline;">
                    <label for="itemId">Index to be deleted:</label>
                    <input type="text" id="itemid" name="itemId" class="text-input">
                    <input type="submit" value="delete" class="submit-button-add">
                </form>
</div>
<!-- Get the list of items from the database and display them in a table-->
<!-- method: GET
     requirements: none
     returns: index, name, description, isCompleted-->

<table>
    <!-- create a table row with all header names-->
    <tr>
        <th>Index</th>
        <th>Name</th>
        <th>Description</th>
        <th>Is Completed</th>
<%--        <th>Action</th>--%>
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

    </tr>
    <% }} %>
</table>
</body>
</html>
