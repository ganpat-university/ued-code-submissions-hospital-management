package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/register")

public class registration extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String uid = req.getParameter("uid");
		String uname = req.getParameter("uname");
		String upass = req.getParameter("upass");
		String ufname = req.getParameter("ufname");
		String gender = req.getParameter("gender");
		String uemail = req.getParameter("uemail");
		String umobile = req.getParameter("umobile");
		String udob = req.getParameter("udob");
		String uage = req.getParameter("uage");
		String regdate = req.getParameter("regdate");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			PreparedStatement pst = con.prepareStatement("insert into registration values(?,?,?,?,?,?,?,?,?,?);");
			pst.setString(1, uid);
			pst.setString(2, uname);
			pst.setString(3, upass);
			pst.setString(4, ufname);
			pst.setString(5, gender);
			pst.setString(6, uemail);
			pst.setString(7, umobile);
			pst.setString(8, udob);
			pst.setString(9, uage);
			pst.setString(10, regdate);
			int value=pst.executeUpdate();
			
			if(value>0) 
			{
				PrintWriter out=res.getWriter();

				res.setContentType("text/HTML");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"Your Registration is successful\")");
				out.println("window.location.href = \"index.html\";");
				out.println("</script>");
			}
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e);
		}	
	}
}