<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Request Membership</title>
</head>
<body>
    <h2>List of Membership Requests</h2>
    <table border="1">
        <tr>
            <th>RMID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        <%
            try {
                InitialContext ctx = new InitialContext();
                Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ikimina_iprckarongi","root","");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT rmid, firstname, lastname, email,phone FROM request_membership WHERE status='pending'");
                
                while (rs.next()) {
                    int rmid = rs.getInt("rmid");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
        %>
                    <tr>
                        <td><%= rmid %></td>
                        <td><%= firstname %></td>
                        <td><%= lastname %></td>
                        <td><%= email %></td>
                        <td><%= phone %></td>
                        <td><a href="ApproveRequest?rmid=<%= rmid %>">Approve</a></td>
                    </tr>
        <%
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>
