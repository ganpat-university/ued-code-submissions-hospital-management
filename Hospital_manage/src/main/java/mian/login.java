package mian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/login")
public class login extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String uname = req.getParameter("uname");
		String upass = req.getParameter("upass");
		PrintWriter out=res.getWriter();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			Statement stmt = con.createStatement();
			int count=0;
			ResultSet rs = stmt.executeQuery("SELECT * from registration;");
			while(rs.next()) 
			{
				if(rs.getString("username").equals(uname) && rs.getString("password").equals(upass))
				{
					count=1;
					break;
				}
			}
			
			if(count==1) 
			{
				try 
				{
					ResultSet rs1 = stmt.executeQuery("select * from registration where username='" + uname + "';");
					out.println("<table border=\"1\" align=\"center\">");
					out.println("<tr>");
					out.println("<th>User_id</th>");
					out.println("<th>UserName</th>");
					out.println("<th>Password</th>");
					out.println("<th>Full Name</th>");
					out.println("<th>Gender</th>");
					out.println("<th>Email</th>");
					out.println("<th>Mobile Number</th>");
					out.println("<th>DOB</th>");
					out.println("<th>Age</th>");
					out.println("<th>Date of Registration</th>");
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
						out.println("<td>"+rs1.getString(7)+"</td>");
						out.println("<td>"+rs1.getString(8)+"</td>");
						out.println("<td>"+rs1.getString(9)+"</td>");
						out.println("<td>"+rs1.getString(10)+"</td>");
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