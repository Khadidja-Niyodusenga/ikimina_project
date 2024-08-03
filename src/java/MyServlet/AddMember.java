package wego;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet("/addMember")
public class AddMember extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String rmid = request.getParameter("rmid");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String approvedDate = request.getParameter("approved_date");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            InitialContext ctx = new InitialContext();
           Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ikimina_iprckarongi","root","");
           
            // Insert into member table
            PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO member (rmid, firstname, lastname, email, phone, approved_date, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, Integer.parseInt(rmid));
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setDate(6, Date.valueOf(approvedDate));
            pstmt.setString(7, username);
            pstmt.setString(8, password);
            pstmt.executeUpdate();
            // Update the status of the request
            PreparedStatement pstmtUpdate = con.prepareStatement(
                "UPDATE request_membership SET status = 'approved' WHERE rmid = ?");
            pstmtUpdate.setInt(1, Integer.parseInt(rmid));
            pstmtUpdate.executeUpdate();
            pstmt.close();
            pstmtUpdate.close();
            con.close();
            response.sendRedirect("ListRequests.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
