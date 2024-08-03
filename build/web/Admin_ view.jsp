<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin View</title>
</head>
<body>
    <h1>All Membership Requests</h1>
    <a href="goutServlet">Logout</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Request Date</th>
            <th>Reason To Join</th>
            <th>Status</th>
        </tr>
        <% 
            List<AdminRequest.Member> members = (List<AdminRequest.Member>) request.getAttribute("members");
            for (AdminRequest.Member member : members) {
        %>
        <tr>
            <td><%= member.getRmid() %></td>
            <td><%= member.getFirstname() %></td>
            <td><%= member.getLastname() %></td>
            <td><%= member.getEmail() %></td>
            <td><%= member.getPhone() %></td>
            <td><%= member.getRequestDate() %></td>
             <td><%= member.getReasonToJoin() %></td>
            <td><%= member.getStatus() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
