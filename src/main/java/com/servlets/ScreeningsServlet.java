package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.screenings.Screening;
import com.screenings.ScreeningDao;


@WebServlet("/screening")
public class ScreeningsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//initialize variables with the jsp pages servlet redirects to since they are used multiple times
	private static String INSERT = "/c_admin/AddScreenings.jsp";
	private static String EDIT = "/c_admin/EditScreening.jsp";
    private static String LIST_Screenings = "/c_admin/DisplayScreenings.jsp";
    private static String List_Screenings2 = "/customer/CustomerScreenings.jsp";
    private ScreeningDao dao;     
      
    public ScreeningsServlet() {
        super();
        dao = new ScreeningDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String forward="";
	        String action = request.getParameter("action");
	        response.setContentType("text/html; charset=ISO-8859-1");
			response.setCharacterEncoding("ISO-8859-1");
			request.setCharacterEncoding("ISO-8859-1");

			//proceed to appropriate jsp page depending on value of action while calling appropriate dao. methods
	        if (action.equalsIgnoreCase("Delete")){
	            int id = Integer.parseInt(request.getParameter("ID"));
	            dao.deleteMovie(id);
	            forward = LIST_Screenings;
	            request.setAttribute("Screenings", dao.getAllScreenings()); 
	        } else if (action.equalsIgnoreCase("edit")){
	            forward = EDIT;
	            int id = Integer.parseInt(request.getParameter("screeningID"));
	            Screening Screening = dao.getScreeningByID(id);
	            request.setAttribute("Screenings", Screening);
	        } else if (action.equalsIgnoreCase("DisplayScreenings")){ //for c admins
	            forward = LIST_Screenings;
	            request.setAttribute("Screenings", dao.getAllScreenings());	            
	        } else if (action.equalsIgnoreCase("DisplayAllScreenings")) {  //for customers
	            forward = List_Screenings2;
	            request.setAttribute("Screenings", dao.getAllScreenings());
	        } else {
	            forward = INSERT;
	        }
	        
	        request.getRequestDispatcher(forward).forward(request, response);

	    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		request.setCharacterEncoding("ISO-8859-1");
    	String action = request.getParameter("action");
    	
    	//create screening object and store variables from form
    	Screening Screening = new Screening();
        Screening.setMovieName(request.getParameter("MovieName"));
        Screening.setDate(request.getParameter("Date"));
        Screening.setTime(request.getParameter("Time"));

        //int variables need to be parsed which happens inside try/catch        
        String screeningID = request.getParameter("ScreeningID");
		String movieID = request.getParameter("MovieID");
		String cinemaID = request.getParameter("CinemaID");
        String contentAdminID = request.getParameter("ContentAdminID");
        String seatsAvailable = request.getParameter("SeatsAvailable");
		
		try {
            Screening.setScreeningID(Integer.parseInt(screeningID));
            Screening.setMovieID(Integer.parseInt(movieID));
            Screening.setCinemaID(Integer.parseInt(cinemaID));
			Screening.setContentAdminID(Integer.parseInt(contentAdminID));
			Screening.setSeatsAvailable(Integer.parseInt(seatsAvailable));
			
			
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
		
		//if it's an edit or insert, call appropriate method from screeningDao
		if (action.equalsIgnoreCase("insert")){
			   dao.addScreening(Screening);
		}	       
		else if (action.equalsIgnoreCase("edit")) {
			  dao.updateScreening(Screening);
		}
		
		//Choose Screenings between two dates
		if (action.equalsIgnoreCase("DateRange")){
			String startDate = request.getParameter("StartDate");
			String endDate = request.getParameter("EndDate");
			//Go to display screenings page for customers while displaying Screenings list's screenings
			request.setAttribute("Screenings", dao.getScreeningsByDate(startDate, endDate));
			request.getRequestDispatcher(List_Screenings2).forward(request, response);
			
		}
		
		//Go to display screenings page for content admins while displaying Screenings list's (all) screenings
	    request.setAttribute("Screenings", dao.getAllScreenings());
		request.getRequestDispatcher(LIST_Screenings).forward(request, response);
	        
	}

}
