package com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.movies.Movie;
import com.movies.MovieDao;
import com.screenings.ScreeningDao;


@WebServlet("/movie")
public class MoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//initialize variables with the jsp pages servlet redirects to since they are used through if statements
    private static String INSERT = "/c_admin/AddMovies.jsp";
    private static String LIST_Movies = "/c_admin/DisplayMovies.jsp";
    private static String EDIT = "/c_admin/EditMovie.jsp";
    private static String LIST_Screenings = "/c_admin/DisplayScreenings.jsp";
    private static String LIST_CustomerScreenings = "/customer/CustomerScreenings.jsp";
    private static String LIST_CustomerMovies = "/customer/CustomerMovies.jsp";
    private MovieDao dao;  
   
    public MoviesServlet() {
        super();
        dao = new MovieDao();        
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
	            forward = LIST_Movies;
	            request.setAttribute("Movies", dao.getAllMovies());    
	        } else if (action.equalsIgnoreCase("DisplayMovies")){
	            forward = LIST_Movies;
	            request.setAttribute("Movies", dao.getAllMovies());
	        } else if (action.equalsIgnoreCase("DisplayCustomerMovies")) {
	        	forward = LIST_CustomerMovies;
	            request.setAttribute("Movies", dao.getAllMovies());	        
	        } else if (action.equalsIgnoreCase("edit")){
	        	int id = Integer.parseInt(request.getParameter("ID"));
	            forward = EDIT;
	            Movie Movie = dao.getMovieByID(id);
	            request.setAttribute("Movies", Movie);
	        }else if (action.equalsIgnoreCase("DisplayMovieScreenings")) {
	        	int id = Integer.parseInt(request.getParameter("ID"));
	        	//Creating a ScreeningDao object so that i can use the method getMovieScreenings()
	        	ScreeningDao screeningDao = new ScreeningDao();        
	        	forward = LIST_Screenings;
	            request.setAttribute("Screenings", screeningDao.getMovieScreenings(id));	  
	        } else if(action.equalsIgnoreCase("DisplayCustomerScreenings")) {
	        	int id = Integer.parseInt(request.getParameter("ID"));
	        	//creating it inside if statements since  it happens rarely, 2/7 times
	        	ScreeningDao screeningDao = new ScreeningDao();
	        		        
	        	forward = LIST_CustomerScreenings;
	            request.setAttribute("Screenings", screeningDao.getMovieScreenings(id));      	        
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
    	
    	//create movie object and store variables from form
    	Movie Movie = new Movie();
        Movie.setName(request.getParameter("Name"));
        Movie.setContent(request.getParameter("Content"));
        Movie.setType(request.getParameter("Type"));
        Movie.setSummary(request.getParameter("Summary"));
        
        //int variables need to be parsed which happens inside try/catch
        String id = request.getParameter("ID");
        String length = request.getParameter("Length");
        String content_Admin_Id = request.getParameter("Content_admin_Id");
        
        try {
            Movie.setId(Integer.parseInt(id));
            Movie.setLength(Integer.parseInt(length));
            Movie.setCadmin_id(Integer.parseInt(content_Admin_Id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        //if it's an edit or insert, call appropriate method from MovieDao
        if (action.equalsIgnoreCase("edit")){
        	dao.updateMovie(Movie);
        } else if (action.equalsIgnoreCase("insert")){
        	dao.addMovie(Movie);
        }
        
        //Redirect to appropriate jsp page after getting the Movies list with getAllMovies method
        request.setAttribute("Movies", dao.getAllMovies());
		request.getRequestDispatcher(LIST_Movies).forward(request, response);
        
	}

}