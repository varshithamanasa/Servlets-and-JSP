package com.greet; //learn this

import java.io.IOException; //learn this 
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

							//learn	
public class GreetingServlet extends GenericServlet {
	
	//learn ctor
    public GreetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//learn implementation		
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//learn the handle
		String buttonValue = request.getParameter("submit");
		
		//below object is the handle to talk with tomcat
		ServletContext ctx = getServletContext();
		
		InputStream is = null;
			
		if(buttonValue.equals("Word")) {
			response.setContentType("application/msword");
			is = ctx.getResourceAsStream("/WEB-INF/mydata/ShortCut Keys.doc");
		}
		else if(buttonValue.equals("Excel")) {
			response.setContentType("application/vnd.ms-excel");
			is = ctx.getResourceAsStream("/WEB-INF/mydata/Employee.xls");
		}
		else if(buttonValue.equals("PowerPoint")) {
			response.setContentType("application/vnd.ms-powerpoint");
			is = ctx.getResourceAsStream("/WEB-INF/mydata/Presentation on JDBC.ppt");
		}
		else if(buttonValue.equals("PDF")) {
			response.setContentType("application/pdf");
			is = ctx.getResourceAsStream("/WEB-INF/mydata/Chapter 8 Handling Cookies.pdf");
		}
		//PrintWriter pw = response.getWriter();
		//pw.println("text data"); // u can send text data

		//abc.doc  5000 -> 1024 1024 1024 1024 904 
		
		int read=0;
		byte bytes[] = new byte[1024]; //make 1kb array
		
		OutputStream os = response.getOutputStream();
		//using os.write() you can send binary data
		
		while( ( read = is.read(bytes) ) != -1)
     	{
			os.write(bytes, 0, read); // content, 0, tillbytes
		}
		 
		
		os.flush(); // send the entire loop content to the customer from tomcat to the browser
		os.close();
		/*
		 * response.setContentType("application/vnd.ms-excel");
		 * out.println("\tQ1\tQ2\tQ3\tQ4\tTOTAL");
		 * out.println("Apples\t28\t35\t45\t56\t=sum(B2:E2)");
		 * out.println("Mangos\t12\t34\t45\t56\t=sum(B3:E3)");
		 * out.println("Orange\t56\t67\t78\t89\t=sum(B4:E4)");
		 */		/*
		 * //learn io //learn producesA
		 * 
		 * for(int i=1;i<=6;i++) {
		 * pw.println("<h"+i+"> Greeting from Servlet</h"+i+">"); } int num =
		 * Integer.parseInt(request.getParameter("v_dno"));
		 * pw.println("<h2>GreetServlet : num is "+num+"</h2>");
		 * 
		 * RequestDispatcher rd = request.getRequestDispatcher("First.jsp");
		 * rd.include(request, response);// send the control to the jsp page
		 */	}

}
