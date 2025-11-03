package com.screenings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.db.util.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ScreeningDao {
	private Connection connection;
	
	
	public ScreeningDao() {
        connection = DBConnection.createConnection();
    }
	
	//add the screening object method brings into provoles table
	public void addScreening (Screening Screening) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO PROVOLES (Screening_Id, MOVIES_ID, MOVIES_NAME, CINEMAS_ID, CONTENT_ADMIN_ID, Date, Seats_Available, Hour) VALUES (?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Screening.getScreeningID());
            preparedStatement.setInt(2, Screening.getMovieID());
            preparedStatement.setString(3, Screening.getMovieName());
            preparedStatement.setInt(4, Screening.getCinemaID());
            preparedStatement.setInt(5, Screening.getContentAdminID());
            preparedStatement.setString(6, Screening.getDate());
            preparedStatement.setInt(7, Screening.getSeatsAvailable());
            preparedStatement.setString(8, Screening.getTime());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//delete provoles row where screening id equals the one brought by method
	public void deleteMovie(int Id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from PROVOLES where Screening_Id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//update provoles table row where screening_Id equal the screening Id stored in Screening object
    public void updateScreening(Screening Screening) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update provoles set MOVIES_ID=?, MOVIES_NAME=?, CINEMAS_ID=?, CONTENT_ADMIN_ID=?, Date=?, Seats_Available=?, Hour=?" +
                            "where Screening_Id=?");
            // Parameters start with 1
            
            preparedStatement.setInt(1, Screening.getMovieID());
            preparedStatement.setString(2, Screening.getMovieName());
            preparedStatement.setInt(3, Screening.getCinemaID());
            preparedStatement.setInt(4, Screening.getContentAdminID());
            preparedStatement.setString(5, Screening.getDate());
            preparedStatement.setInt(6, Screening.getSeatsAvailable());
            preparedStatement.setString(7, Screening.getTime());
            preparedStatement.setInt(8, Screening.getScreeningID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	//create Screenings list of screening objects with all the rows of provoles table
	public List<Screening> getAllScreenings() {
        List<Screening> Screenings = new ArrayList<Screening>();
        try {       	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from provoles");
            while (rs.next()) {
            	//create each Screening object
                Screening Screening = new Screening();
                Screening.setScreeningID(rs.getInt("Screening_Id"));
                Screening.setMovieID(rs.getInt("MOVIES_ID"));
                Screening.setMovieName(rs.getString("MOVIES_NAME"));
                Screening.setCinemaID(rs.getInt("CINEMAS_ID"));
                Screening.setContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
                Screening.setDate(rs.getString("Date"));
                Screening.setSeatsAvailable(rs.getInt("Seats_Available"));
                Screening.setTime(rs.getString("Hour"));
                //Store screening object into Screenings list
                Screenings.add(Screening);
                //System.out.println(Screening);  //for debugging purposes
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the compleeted list
        return Screenings;
    }
	
	////create Screenings list of screening objects with the rows of provoles table where a specific movie is being refered by int ID brought by method call
	public List<Screening> getMovieScreenings(int ID) {
        List<Screening> ProvolesTainias = new ArrayList<Screening>();
        try {       	
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM provoles WHERE MOVIES_ID=?");
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Screening Screening = new Screening();
                Screening.setScreeningID(rs.getInt("Screening_Id"));
                Screening.setMovieID(rs.getInt("MOVIES_ID"));
                Screening.setMovieName(rs.getString("MOVIES_NAME"));
                Screening.setCinemaID(rs.getInt("CINEMAS_ID"));
                Screening.setContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
                Screening.setDate(rs.getString("Date"));
                Screening.setSeatsAvailable(rs.getInt("Seats_Available"));
                Screening.setTime(rs.getString("Hour"));
                ProvolesTainias.add(Screening);
                //System.out.println(Screening);  //for debugging purposes
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ProvolesTainias;
	}
	
	//similar with the above but it requires two String variables on call
	public List<Screening> getScreeningsByDate(String sDate, String eDate) {
        List<Screening> ProvolesTainias = new ArrayList<Screening>();  
        //first create a dateFormat object with european date conventions as the dates stored inprovoles table
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");        
        Date startDate = null;
        Date endDate = null;
        //try to parse strings from method call into date format specified above
        try {
            startDate = dateFormat.parse(sDate);
            endDate = dateFormat.parse(eDate);
        } catch (ParseException e) {
            System.out.println("Failed to parse date: " + e.getMessage());
        } 
        //as with above methods, call everything from provoles table and create according object for every row in loop
        try {       	
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from provoles");
            while (rs.next()) {            	
                Screening Screening = new Screening();
                Screening.setScreeningID(rs.getInt("Screening_Id"));
                Screening.setMovieID(rs.getInt("MOVIES_ID"));
                Screening.setMovieName(rs.getString("MOVIES_NAME"));
                Screening.setCinemaID(rs.getInt("CINEMAS_ID"));
                Screening.setContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
                Screening.setDate(rs.getString("Date"));
                Screening.setSeatsAvailable(rs.getInt("Seats_Available"));
                Screening.setTime(rs.getString("Hour"));
                
                //but this time add it in the list ONLY if the dates are between the specified by user dates
                try {
					Date screeningDate = dateFormat.parse(Screening.getDate());
					if (startDate.before(screeningDate) && (endDate.after(screeningDate))) {
						ProvolesTainias.add(Screening);
					}
				} catch (ParseException e) {
					System.out.println("Failed to parse date: " + e.getMessage());
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the list of screening objects
        return ProvolesTainias;
	}
	
	
	 //return a screening object that has a specified movie through it's ID
	public Screening getScreeningByID(int ID) {
        Screening Screening = new Screening();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from provoles where Screening_Id=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Screening.setScreeningID(rs.getInt("Screening_Id"));
                Screening.setMovieID(rs.getInt("MOVIES_ID"));
                Screening.setMovieName(rs.getString("MOVIES_NAME"));
                Screening.setCinemaID(rs.getInt("CINEMAS_ID"));
                Screening.setContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
                Screening.setDate(rs.getString("Date"));
                Screening.setSeatsAvailable(rs.getInt("Seats_Available"));
                Screening.setTime(rs.getString("Hour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Screening;
	}
	


}
