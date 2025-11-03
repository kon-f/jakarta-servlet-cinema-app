package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.bookings.Booking;
import com.bookings.BookingDao;



@WebServlet("/booking")
public class BookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//initialize variables with the jsp pages servlet redirects to since they are used multiple times
	 private static String INSERT = "/customer/AddReservation.jsp";
	 private static String LIST_Reservations = "/customer/DisplayReservations.jsp";
	 private BookingDao dao; 
	
    public BookingsServlet() {
        super();
    	dao = new BookingDao();
    } 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward="";
	     String action = request.getParameter("action");
	     response.setContentType("text/html; charset=ISO-8859-1");
		 response.setCharacterEncoding("ISO-8859-1");
		 request.setCharacterEncoding("ISO-8859-1");

		//proceed to appropriate jsp page depending on value of action while calling appropriate dao. methods
	     if (action.equalsIgnoreCase("DisplayBookings")){
	         forward = LIST_Reservations;
	         request.setAttribute("Bookings", dao.getAllBookings());	            
	     }  else if (action.equalsIgnoreCase("CustomersReservations")){
	         forward = LIST_Reservations;
	         HttpSession session = request.getSession();
	         String userName = (String) session.getAttribute("Customer");
	         request.setAttribute("Bookings", dao.getCustomerBookings(userName));
	     } else {
	    	 HttpSession session = request.getSession();
	         String userName = (String) session.getAttribute("Customer");
	    	 int screeningID = Integer.parseInt(request.getParameter("screeningID"));
	    	 forward = INSERT;
	         Booking Booking = dao.setBookingIDs(screeningID, userName);
	         request.setAttribute("Booking", Booking);
	     }
	        
	     request.getRequestDispatcher(forward).forward(request, response);

	}
		


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		request.setCharacterEncoding("ISO-8859-1");
    	String action = request.getParameter("action");
	
    	//create booking object and store variables from form
    	Booking Booking = new Booking();
    	
    	//int variables need to be parsed which happens inside try/catch     
    	String screeningID = request.getParameter("ScreeningID");
        String customerID = request.getParameter("CustomerID");
        String seatsBooked = request.getParameter("SeatsBooked");
        String reservationID = request.getParameter("ReservationID");
        
        try {
        	Booking.setScreeningID(Integer.parseInt(screeningID));
        	Booking.setCustomerID(Integer.parseInt(customerID));
        	Booking.setSeatsBooked(Integer.parseInt(seatsBooked));
        	Booking.setReservationID(Integer.parseInt(reservationID));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        //if a booking is being added do it through boolean addBooking method which returns a true value if there weren't enough available seats 
        if (action.equalsIgnoreCase("insert")){
        	boolean failure = dao.addBooking(Booking);
        	if (failure) {
        		//set error message to insufficient seats and display it by recalling the addReservation page
        		String negativeSeats = "Insufficient Seats";
        		request.setAttribute("errMessage", negativeSeats);
        		request.getRequestDispatcher("customer/AddReservation.jsp").forward(request, response);
        	}
        }
        
        //Get bookings the customer in current session has
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("Customer");
        request.setAttribute("Bookings", dao.getCustomerBookings(userName));
		request.getRequestDispatcher(LIST_Reservations).forward(request, response);
	}

}
