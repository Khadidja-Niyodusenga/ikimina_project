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
import java.sql.ResultSet;
import javax.naming.InitialContext;

@WebServlet("/approveRequest")
public class ApproveRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rmid = Integer.parseInt(request.getParameter("rmid"));
        try {
            InitialContext ctx = new InitialContext();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ikimina_iprckarongi","root","");
            PreparedStatement pstmt = con.prepareStatement(
                "SELECT firstname, lastname, email,phone FROM request_membership WHERE rmid = ?");
            pstmt.setInt(1, rmid);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                request.setAttribute("rmid", rmid);
                request.setAttribute("firstname", rs.getString("firstname"));
                request.setAttribute("lastname", rs.getString("lastname"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("phone", rs.getString("phone"));
                request.getRequestDispatcher("/EnterDetails.jsp").forward(request, response);
            }
            
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
