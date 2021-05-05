package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	public class EmployeeServlet extends HttpServlet  {
		private static final long serialVersionUID = 1L;
		ResultSet rs;
		Statement st;
		PreparedStatement pst;
    public EmployeeServlet() {
        super();
       System.out.println("constructor called....");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("trying to load driver");
			Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("driver is loaded");
				System.out.println("trying to get connection");
			     Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl1","system","manager");
				System.out.println("connected to the database");
			response.setContentType("text/html");
			
			System.out.println("Trying to make a PreparedStatement for DML(update)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			pst = conn.prepareStatement("select * from emp");
			ResultSet rs=pst.executeQuery();
			
			PrintWriter pw=response.getWriter();
			pw.println("<html><body><table border=1><tr><th>empno</th><th>ename</th><th>job</th><th>mgr</th><th>hiredate</th><th>salary</th><th>comm</th><th>deptno</th></tr>");
		     while(rs.next())
		     {
		    	 pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+
		    			 	"<td>"+rs.getString(2)+"</td><td>"+
		    			 	"<td>"+rs.getString(3)+"</td><td>"+
		    			 	"<td>"+rs.getInt(4)+"</td><td>"+
		    			 	"<td>"+rs.getString(5)+"</td><td>"+
		    			 	"<td>"+rs.getInt(6)+"</td><td>"+
		    			 	"<td>"+rs.getInt(7)+"</td><td>"+
		    			 	"<td>"+rs.getInt(8)+"</td></tr>");
		     }
			pw.println("</table></body></table>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}