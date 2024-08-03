/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MyServlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminRequest extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ikimina_iprckarongi";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        List<Member> members = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
             String DB_URL="jdbc:mysql://localhost:3306/ikimina_iprckarongi";
            String DB_USER="root";
            String DB_PASSWORD="";
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL select statement
            String sql = "SELECT * FROM request_membership";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Process result set
            while (rs.next()) {
                int rmid = rs.getInt("rmid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String requestDate = rs.getString("request_date");
                String reasontojoin = rs.getString("reason_to_join");
                String status = rs.getString("status");

                members.add(new Member(rmid, firstname, lastname, email, phone, requestDate,reasontojoin, status));
            }

            // Forward to JSP
            request.setAttribute("members", members);
            request.getRequestDispatcher("jsp/Admin_view.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Handle error
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public class Member {
        private int rmid;
        private String firstname;
        private String lastname;
        private String email;
        private String phone;
        private String requestDate;
        private String status;

        // Constructor and getters
        public Member(int rmid, String firstname, String lastname, String email, String phone, String requestDate, String reasontojoin, String status1) {
            this.rmid = rmid;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.phone = phone;
            this.requestDate = requestDate;
            this.status = reasontojoin;
        }

        // Getters
        public int getRmid() { return rmid; }
        public String getFirstname() { return firstname; }
        public String getLastname() { return lastname; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getRequestDate() { return requestDate; }
        public String getStatus() { return status; }
    }
}
