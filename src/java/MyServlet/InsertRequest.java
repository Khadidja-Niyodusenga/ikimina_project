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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/InsertServlet")
public class InsertRequest extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ikimina_iprckarongi";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
         String request_date = request.getParameter("request_date");
          String reason_to_join = request.getParameter("reason_to_join");
        String status = "pending"; // Default status

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String requestDate = sdf.format(new Date());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL="jdbc:mysql://localhost:3306/ikimina_iprckarongi";
            String DB_USER="root";
            String DB_PASSWORD="";
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL insert statement
            String sql = "INSERT INTO request_membership (firstname, lastname, email, phone, request_date,reason_to_join, status) VALUES (?, ?,?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, requestDate);
             stmt.setString(6, reason_to_join);
            stmt.setString(7, status);

            // Execute update
            stmt.executeUpdate();
            
            // Redirect to thank you page
            response.sendRedirect("jsp/thank.jsp?lastname=" + lastname);

        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
            // Redirect to error page
            response.sendRedirect("error.jsp");
        } 
    }
}
