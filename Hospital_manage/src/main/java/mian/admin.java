package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/admin")
public class admin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String uname = req.getParameter("username");
		String upass = req.getParameter("password");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			Statement stmt = con.createStatement();
			int count=0;
			ResultSet rs = stmt.executeQuery("SELECT * from admin;");
			while(rs.next()) 
			{
				if(rs.getString("username").equals(uname) && rs.getString("password").equals(upass))
				{
					count=1;
					break;
				}
			}
			PrintWriter out=res.getWriter();
			if(count==1) 
			{
				try 
				{
					ResultSet rs1 = stmt.executeQuery("select * from appointment;");
					out.println("<table border=\"1\" align=\"center\">");
					out.println("<tr>");
					out.println("<th>Patient Name</th>");
					out.println("<th>Contact No.</th>");
					out.println("<th>Doctor</th>");
					out.println("<th>Date</th>");
					out.println("<th>Time</th>");
					out.println("<th>Reason of Visit</th>");
					out.println("</tr>");
					while(rs1.next()) 
					{
						out.println("<tr>");
						out.println("<td>"+rs1.getString(1)+"</td>");
						out.println("<td>"+rs1.getString(2)+"</td>");
						out.println("<td>"+rs1.getString(3)+"</td>");
						out.println("<td>"+rs1.getString(4)+"</td>");
						out.println("<td>"+rs1.getString(5)+"</td>");
						out.println("<td>"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("</table>");
				} 
				
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(count==0)
			{
				//PrintWriter out=res.getWriter();
				
				res.setContentType("text/HTML");
				//out.println("<script type=\"text/javascript\">");
				out.println("Wrong username or Password. Try Again..");
				//out.println("window.location.href = \"login.html\";");
				//out.println("</script>");
				
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e);
		}	
	}
}