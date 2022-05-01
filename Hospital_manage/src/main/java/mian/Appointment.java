package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/appo")

public class Appointment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String name = req.getParameter("Aname");
		String cont = req.getParameter("Acont");
		String doc = req.getParameter("doc");
		String date = req.getParameter("Adate");
		String time = req.getParameter("Atime");
		String reason = req.getParameter("reas");
		
		PrintWriter out=res.getWriter();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			PreparedStatement pst = con.prepareStatement("insert into appointment values(?,?,?,?,?,?);");
			pst.setString(1, name);
			pst.setString(2, cont);
			pst.setString(3, doc);
			pst.setString(4, date);
			pst.setString(5, time);
			pst.setString(6, reason);
			int value=pst.executeUpdate();
			
			if(value>0) 
			{
				res.setContentType("text/HTML");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"Your Appointment has been registered\")");
				out.println("window.location.href = \"index.html\";");
				out.println("</script>");
			}
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			out.println(e);
		}
	}
}