package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/sandipmodh")

public class sandipmodh extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter out=res.getWriter();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from appointment where doctor='Dr. Sandip Modh';");
			out.println("<table border=\"1\" align=\"center\">");
			out.println("<tr>");
			out.println("<th>Patient Name</th>");
			//out.println("<th>UserName</th>");
			//out.println("<th>Password</th>");
			//out.println("<th>Gender</th>");
			out.println("<th>Date</th>");
			out.println("<th>Time</th>");
			//out.println("<th>Address</th>");
			out.println("</tr>");
			while(rs.next()) 
			{
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"</td>");
				//out.println("<td>"+rs.getString(2)+"</td>");
				//out.println("<td>"+rs.getString(3)+"</td>");
				//out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				//out.println("<td>"+rs.getString(7)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} 
		
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
