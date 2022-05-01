package mian;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

@WebFilter(urlPatterns="/doctor")
public class DoctorL implements Filter
{
public void init() {}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain ch) throws IOException,ServletException 
	{
		try
		{
			PrintWriter out = res.getWriter();
			String username=req.getParameter("user");
			String Pass=req.getParameter("pass");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital","root","Nrupa@72");
			Statement st=con.createStatement();
			int f=0;
			String q="select * from doctor";
			ResultSet rs=st.executeQuery(q);
			while(rs.next())
			{
				if(rs.getString(1).equals(username) && rs.getString(2).equals(Pass))
				{
					f=1;
					break;
				}
			}
			
			if(f==1)
			{
				out.println("<a href=\""+ username + "\">View Appointment</a>");
				//RequestDispatcher rd=req.getRequestDispatcher("jayeshraval");
				//rd.forward(req, res);
			}
			else
			{
				out.print("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Incorrect Username or password....!</h1>");
				out.println("<div style=\"text-align:center\">");
				out.println("<a href=\"doctorlogin.html\">Try again</a>");
				out.println("</div>");
				out.println("</body>");
			}
		}
		catch(IOException | SQLException e) 
		{
			System.out.println(e);
		}
	}
	public void destroy() {}
}