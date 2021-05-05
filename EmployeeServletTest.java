package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class EmployeeServletTest
 */
public class EmployeeServletTest extends GenericServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs;
	Statement st;
    /**
     * @see GenericServlet#GenericServlet()
     */
    public EmployeeServletTest() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("constructor called....");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Start");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		 System.out.println("destory....");
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("trying to load driver");
			Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("driver is loaded");
				System.out.println("trying to get connection");
			     Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl1","system","manager");
				System.out.println("connected to the database");
			response.setContentType("text/html");
			
			System.out.println("Trying to make a PreparedStatement for DML(update)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("select * from emp");
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
		    			 	"<td>"+rs.getInt(8)+"</td></tr>"+
		    			 	"<td>"+rs.getInt(9)+"</td></tr>");
		     }
			pw.println("</table></body></table>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
