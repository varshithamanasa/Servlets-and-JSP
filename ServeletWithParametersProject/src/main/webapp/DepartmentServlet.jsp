<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>

<%@page import="java.util.Iterator"%>
<%@page import="dept.Department"%>
<%@page import="exception.DepartmentNotFoundException"%>
<%@page import="dept.DepartmentDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Connecting to SQL Database From Department JSP </h2>
	<%!DepartmentDAOImpl ddiObj; %>
	<%!Department deptobj1;  %>
		<%!	  public void jspInit( ) {
				System.out.println("This is init...from Department-jsp");
				ddiObj= new DepartmentDAOImpl();
			}
		%>
		<%!
	  		public void jspDestroy( ) {
			System.out.println("\tThis is destroy...from Department-jsp");
		}
		%>
		<%
		System.out.println("Service....");
		
		int bv = Integer.parseInt(request.getParameter("submit"));
		switch (bv) {
		
		case 1: int dno;
			String dnm;
			String dloc;
						dno = Integer.parseInt(request.getParameter("v_deptno"));
						dnm = request.getParameter("v_dname");
						dloc = request.getParameter("v_loc");
						deptobj1=new Department();
						deptobj1.setDepartmentNumber(dno);
						deptobj1.setDepartmentName(dnm);
						deptobj1.setDepartmentLocation(dloc);
			try {
				ddiObj.addDepartment(deptobj1);
				out.println("Department is added to the Database");
			} catch (Exception e) {
				out.println("<h3>"+e.getMessage()+"</h3>");
				
			}
			out.println("<br><a href = 'DepartmentsList.html'>Dept List</a><br> ");
			
		break;
		case 2: 
				
			    dno =Integer.parseInt(request.getParameter("v_deptno"));
				 dnm = request.getParameter("v_dname");
				 dloc = request.getParameter("v_loc");
				deptobj1=new Department();
				deptobj1.setDepartmentNumber(dno);
				deptobj1.setDepartmentName(dnm);
				deptobj1.setDepartmentLocation(dloc);
				
				try {
					ddiObj.modifyDepartment(deptobj1);
					out.println("Modified Departmet Details of : "+dno);
				} catch (DepartmentNotFoundException e) {
					out.println("<h2>"+e.getMessage()+"!!!</h2>");
					
				}
				
				out.println("<br><a href = 'DepartmentsList.html'>Dept List</a><br> ");
				 
				
		break;
		case 3:  try {
				dno =Integer.parseInt(request.getParameter("v_deptno"));
						deptobj1 = ddiObj.findDepartment(dno);
						
						out.println("<table border =5 cellpadding=10 cellspacing=10");
						out.println("<tr>");
							out.println("<td>"+deptobj1.getDepartmentNumber()+"</td>");
							out.println("<td>"+deptobj1.getDepartmentName()+"</td>");
							out.println("<td>"+deptobj1.getDepartmentLocation()+"</td>");
							out.println("</tr>");
						out.println("<h2> Results found</h2>");
			} 
			catch (NullPointerException e) {
				out.println("<h1>Dept not found!!!!</h1>");
				System.out.println("Dept not found");
			}
		break;
		
		case 4: 
			out.println("<h2> Results found</h2>");
			out.println("<table border=5 cellspacing=10 cellpadding=10>");
			out.println("<th>");		out.println("DEPT NO");	out.println("</th>");
			out.println("<th>");		out.println("DEPT NAME");out.println("</th>");
			out.println("<th>");		out.println("LOCATION"); out.println("</th>");
			List<Department> deptList = ddiObj.findDepartments();
			Iterator<Department> deptIter = deptList.iterator();
			
				while(deptIter.hasNext()) {
					Department deptObj = deptIter.next();
					out.println("<tr>");
					
						out.println("<td>");
						out.println(deptObj.getDepartmentNumber());
						out.println("</td>");
					
						out.println("<td>");
						out.println(deptObj.getDepartmentName());
						out.println("</td>");
						out.println("<td>");
						out.println(deptObj.getDepartmentLocation());
						out.println("</td>");
							
					out.println("</tr>");
				}
			out.println("</table>");
		break;
		case 5:  
				dno =Integer.parseInt(request.getParameter("v_deptno"));
						deptobj1=new Department();
						deptobj1.setDepartmentNumber(dno);
						
			try {
				ddiObj.removeDepartment(deptobj1);
				out.println("Deparment "+dno+" Deleted ");
			} catch (DepartmentNotFoundException e) {
				
				out.println("<h2>"+e.getMessage()+"!!!</h2>");
			}
						
				out.println("<br><a href = 'DepartmentsList.html'>Dept List</a><br> ");
					
		break;
		
		}
		out.println("<a href='http://localhost:8080/ServletwithPArametersProject/'> Back to Home </a>");
		%>
	
</body>
</html>