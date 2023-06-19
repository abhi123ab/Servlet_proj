package org.jsp.PostApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
{
	//fetch ui form data
 String sid=req.getParameter("i");//"1"
 int id=Integer.parseInt(sid);
 
 String name=req.getParameter("nm");
 String dept=req.getParameter("dp");
 String sperc=req.getParameter("pr");
 double perc=Double.parseDouble(sperc);
 
 PrintWriter out=resp.getWriter();
 out.println("<html><body bgcolor='gray'>"
	 		+ "<h1>Student name is "+name+" from "+dept+" Record submitted"+"</h1>"
				+ "</body></html>");
 
 out.close();
 
//persistence logic//jdbc technology
 Connection con=null;
 PreparedStatement pstmt=null;
 String qry="insert into btm1.student values(?,?,?,?)";
 try {
	Class.forName("com.mysql.jdbc.Driver");
	
	//2nd step of jdbc
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
	//3rd step of jdbc
	pstmt=con.prepareStatement(qry); //pre compilation
	
	//set the data for placeholder
        pstmt.setInt(1, id);//converted id we here passing
        pstmt.setString(2, name);
        pstmt.setDouble(3, perc);
        pstmt.setString(4, dept);
        
    //Execution 
        pstmt.executeUpdate();
        System.out.println("Record inserted succesfully");
} 
 catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
}
 finally
 {
	 if(pstmt!=null)
	 {
		 try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 if(con!=null)
	 {
		 try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
 }
 
}

}
