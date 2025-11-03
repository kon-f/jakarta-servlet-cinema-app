package com.servlets;

import java.io.IOException;

import com.login.LoginBean;
import com.login.LoginDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LogInServlet() {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get/set user typed strings and create LoginBean and LoginDao objects
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(userName);
		loginBean.setPassword(password);
		LoginDao loginDao = new LoginDao();
		try
		{
			//Validate user's credentials and act according to user's role if authenticated
			String userValidate = loginDao.authenticateUser(loginBean);
			if(userValidate.equals("Admin_Role"))
			{
				System.out.println("Admin Login");
				HttpSession session = request.getSession(); //Creating a session
				session.setAttribute("Admin", userName); //setting session attribute
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response); //Go to admin.jsp page
			}
			else if(userValidate.equals("Content_Admin_Role"))
			{
				System.out.println("Content Admin Login");
				HttpSession session = request.getSession();
				session.setAttribute("Cadmin", userName);
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("/c_admin/content_admin_login.jsp").forward(request, response); //Go to c_admin.jsp page
			}
			else if(userValidate.equals("Customer_Role"))
			{
				System.out.println("Customer Login");
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("Customer", userName);
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("/customer/customer_login.jsp").forward(request, response); //Go to customer.jsp page
			}
			else
			{   //User not validated, refresh login page and show error message
				System.out.println("Error message = "+userValidate);
				request.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}

}
