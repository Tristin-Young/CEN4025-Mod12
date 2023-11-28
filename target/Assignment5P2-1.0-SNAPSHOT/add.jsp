
<!DOCTYPE html>
<html>
<head>
    <title>Add New Item</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Add New Item</h1>
    <form action="todo" method="POST" class="form-style">
        <input type="hidden" name="action" value="add">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" class="text-input">
        <label for="itemDesc">Item Description:</label>
        <input type="text" id="itemDesc" name="itemDesc" class="text-input">
        <input type="submit" value="Add" class="submit-button-add">
    </form>
</div>
</body>
</html>

