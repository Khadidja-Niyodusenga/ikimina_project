<html>
<head>
    <title>Enter Member Details</title>
</head>
<body>
    <h2>Enter Member Details</h2>
    <form action="AddMember" method="post">
        <input type="hidden" name="rmid" value="<%= request.getAttribute("rmid") %>" />
        <input type="hidden" name="firstname" value="<%= request.getAttribute("firstname") %>" />
        <input type="hidden" name="lastname" value="<%= request.getAttribute("lastname") %>" />
        <input type="hidden" name="email" value="<%= request.getAttribute("email") %>" />
        <input type="hidden" name="phone" value="<%= request.getAttribute("phone") %>" />
                <label>Approved Date:</label>
        <input type="date" name="approved_date" required /><br/>
                <label>Username:</label>
        <input type="text" name="username" required /><br/>
                <label>Password:</label>
        <input type="password" name="password" required /><br/>
                <input type="submit" value="Submit"/>
    </form>
</body>
</html>
