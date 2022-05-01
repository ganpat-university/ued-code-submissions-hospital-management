package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/review")

public class review extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String name = req.getParameter("name");
		String review = req.getParameter("rev");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			PreparedStatement pst = con.prepareStatement("insert into review values(?,?);");
			pst.setString(1, name);
			pst.setString(2, review);
			int value=pst.executeUpdate();
			
			if(value>0) 
			{
				PrintWriter out=res.getWriter();

				res.setContentType("text/HTML");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"Review Added Successfully....\")");
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