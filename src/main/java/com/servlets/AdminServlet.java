package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.admins.User;
import com.admins.UserDao;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//initialize variables with the jsp pages servlet redirects to since they are used multiple times
	 private static String INSERT_Customer = "/admin/addCustomer.jsp";
	 private static String LIST_Customers = "/admin/CustomersDisplay.jsp";
	 private static String INSERT_CAdmin = "/admin/addCAdmin.jsp";
	 private static String LIST_CAdmins = "/admin/CAdminDisplay.jsp";
	private UserDao dao;    
    
    public AdminServlet() {
        super();
        dao = new UserDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
	    String action = request.getParameter("action");
	    response.setContentType("text/html; charset=ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		request.setCharacterEncoding("ISO-8859-1");

   	    User User = new User(); 
   	    User.setUsername(request.getParameter("Username"));
   	    User.setId(0);
   	    User.setName("");
   	    //proceed to appropriate jsp page depending on value of action while calling appropriate dao. methods
		if (action.equalsIgnoreCase("DisplayCAdmins")){
	         forward = LIST_CAdmins;
	         request.setAttribute("CAdmins", dao.getAllCAdmins());	            
	     }  else if (action.equalsIgnoreCase("DisplayCustomers")){
	         forward = LIST_Customers;
	         request.setAttribute("Customers", dao.getAllCustomers());
	     }  else if (action.equalsIgnoreCase("editc")) {

	         forward = INSERT_CAdmin;	
	         request.setAttribute("User", User);
	     } else {
	    	
	         forward = INSERT_Customer;
	         request.setAttribute("User", User);
	     }
  
	     request.getRequestDispatcher(forward).forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		request.setCharacterEncoding("ISO-8859-1");
    	String action = request.getParameter("action");
    	
    	//create User object and store variables from form
    	User User = new User();
    	
    	User.setName(request.getParameter("Name"));
    	User.setUsername(request.getParameter("Username"));
    	
    	//int values need to be parsed in try/catch
    	String id = request.getParameter("ID");
    	String userName = request.getParameter("Username");
        try {
        	User.setId(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        String role="";
        //Do you want to demote a cadmin into a customer?
        if (action.equalsIgnoreCase("edit")){
        	role = "Customer";
        	dao.deleteCAdmin(userName); //Then delete user from Content_admin table
        	dao.changeUserRole(userName, role); //Change his role in user table into Customer
        	dao.addCustomer(User); // and finally add his info to customer table
        	//want to promote a customer into a cadmin?
        } else if (action.equalsIgnoreCase("editc")){
        	role = "Content Admin";
	        dao.deleteCustomer(userName); //Then delete him from customers table
        	dao.changeUserRole(userName, role); // Change his role into content Admin in the user table
        	dao.addCAdmin(User); //And add him into content_admin table
        }
        
        //if you demoted a cAdmin or you simply want to see all customers, display appropriate jsp with appropriate list
        if (action.equalsIgnoreCase("insert") || action.equalsIgnoreCase("DisplayCustomers")){
        	request.setAttribute("Customers", dao.getAllCustomers());
     		request.getRequestDispatcher(LIST_Customers).forward(request, response);
        }
      //if you promoted a customer or you just want to see all customers display appropriate jsp with appropriate list
        else {
        	request.setAttribute("CAdmins", dao.getAllCAdmins());
     		request.getRequestDispatcher(LIST_CAdmins).forward(request, response);
        }
       	
       
	}

}
