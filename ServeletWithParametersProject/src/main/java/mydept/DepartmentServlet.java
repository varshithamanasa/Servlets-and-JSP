package mydept;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dept.Department;
import dept.DepartmentDAOImpl;
import exception.DepartmentNotFoundException;

/**
 * Servlet implementation class DepartmentServlet
 */
public class DepartmentServlet extends GenericServlet {
	
    DepartmentDAOImpl ddi = new DepartmentDAOImpl();
    
    public DepartmentServlet() {
        super();
        System.out.println("Department Servlet..ctor....");
    }

	public void init(ServletConfig config) throws ServletException {
        System.out.println("Department init()...");
	}

	
	public void destroy() {
        System.out.println("Department Servlet...destroy()....");
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("Department Servlet...service()....");
		
		String buttonValue = request.getParameter("submit");
		PrintWriter pw = response.getWriter();
		pw.println("Button clicked value is "+buttonValue);
		
		if(buttonValue.equals("Add Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
		
			Department deptObj = new Department(); //blank object
			deptObj.setDepartmentNumber(dno);// fill up html value
			deptObj.setDepartmentName(dnm); //fill up html value
			deptObj.setDepartmentLocation(dloc); //fill up html value
		
			ddi.addDepartment(deptObj);
			pw.println("<h4>Department is added to the database</h4>");
		}
		else if (buttonValue.equals("Find Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values
			Department deptObj = ddi.findDepartment(dno);
			pw.println("<table border=5 cellpadding=10 cellspacing=10>");
			pw.println("<tr>");
				pw.println("<td>"+deptObj.getDepartmentNumber()+"</td>");
				pw.println("<td>"+deptObj.getDepartmentName()+"</td>");
				pw.println("<td>"+deptObj.getDepartmentLocation()+"</td>");
			pw.println("</tr>");
			pw.println("</table>");
		}
		else if(buttonValue.equals("Find All Depts")) 
		{
			pw.println("<table border=1 cellspacing=10 cellpadding=10>");
			pw.println("<th>");		pw.println("DEPT NO");	pw.println("</th>");
			pw.println("<th>");		pw.println("DEPT NAME");pw.println("</th>");
			pw.println("<th>");		pw.println("LOCATION"); pw.println("</th>");
			List<Department> deptList = ddi.findDepartments();
			Iterator<Department> deptIter = deptList.iterator();
			
				while(deptIter.hasNext()) {
					Department deptObj = deptIter.next();
					pw.println("<tr>");
					
							pw.println("<td>");
								pw.println(deptObj.getDepartmentNumber());
							pw.println("</td>");
					
							pw.println("<td>");
								pw.println(deptObj.getDepartmentName());
							pw.println("</td>");

							pw.println("<td>");
								pw.println(deptObj.getDepartmentLocation());
							pw.println("</td>");
							
					pw.println("</tr>");
				}
			pw.println("</table>");
		}
		else if (buttonValue.equals("Modify Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
			Department deptObj = new Department(); //blank one
			deptObj.setDepartmentNumber(dno);
			deptObj.setDepartmentName(dnm);
			deptObj.setDepartmentLocation(dloc);
			
			try {
				ddi.modifyDepartment(deptObj);
			} catch (DepartmentNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("<h1> Department modify issue :"+e.getMessage()+"</h1>");
				
			}
			pw.println("<h4> Department is modified</h4>");
		}
		else if (buttonValue.equals("Delete Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			
			Department deptObj = new Department(); //blank one
			deptObj.setDepartmentNumber(dno);

			ddi.removeDepartment(deptObj);
			pw.println("<h4> Department is deleted</h4>");
			
		}
		
		pw.println("<a href='http://localhost:8080/ServeletWithParametersProject/'> Back to Welcome </a>");
			
		
	}

}
