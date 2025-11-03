package com.bookings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.db.util.DBConnection;

public class BookingDao {
	 private Connection connection;

	 public BookingDao() {
	     connection = DBConnection.createConnection();
	 }
	 
	 //Boolean add Booking. Returns true if it fails to add a booking due to insufficient seats
	 //Otherwise, it updates provoles table with the reduced seats availability (subtracting the new booked tables)
	 //and then store into reservations table the new reservation
	 public boolean addBooking (Booking Booking) {
		    boolean failure = false;
		    try {
		    	int seatsBooked = Booking.getSeatsBooked();
		    	String sql = "UPDATE provoles SET Seats_Available = Seats_Available - ? WHERE Seats_Available >= ? AND Screening_Id=?";		    	
		    	// Check if the resulting value will be non-negative and execute it
		     
		        PreparedStatement preparedStatement = connection.prepareStatement(sql);
		    	preparedStatement.setInt(1, seatsBooked);
		        preparedStatement.setInt(2, seatsBooked);
		        preparedStatement.setInt(3, Booking.getScreeningID());
	            int rowsAffected = preparedStatement.executeUpdate();
	            
	            //no table row was affected since result was negative, therefore the seats were not sufficient. change failure to true
	            if (rowsAffected == 0) {
	                failure = true;
	                return failure;
	            }
		    } catch (SQLException e) {
	            e.printStackTrace();
	        }
		    //There were enough seats, so make the booking(store it in reservations)
	        try {
	            PreparedStatement preparedStatement = connection.
	            		prepareStatement("INSERT INTO reservations (PROVOLES_Screening_Id, CUSTOMERS_ID, SEATS_Booked, ReservationID) VALUES (?,?,?,?)");

	            preparedStatement.setInt(1, Booking.getScreeningID());
	            preparedStatement.setInt(2, Booking.getCustomerID());
	            preparedStatement.setInt(3, Booking.getSeatsBooked());
	            preparedStatement.setInt(4, Booking.getReservationID());
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return failure;
	    }
	 
	 //Get all bookings in a list (Not used by the program though)
	 public List<Booking> getAllBookings() {
	        List<Booking> Bookings = new ArrayList<Booking>();
	        try {       	
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from reservations");
	            while (rs.next()) {
	                Booking Booking = new Booking();
	                Booking.setReservationID(rs.getInt("ReservationID"));
	                Booking.setScreeningID(rs.getInt("PROVOLES_Screening_Id"));
	                Booking.setCustomerID(rs.getInt("CUSTOMERS_ID"));
	                Booking.setSeatsBooked(rs.getInt("SEATS_Booked"));
	                Bookings.add(Booking);
					
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return Bookings;
	    }
	 
	 //Set a specific reservation screening, customer id and reservation ID
	 public Booking setBookingIDs(int screenID, String username) {
		 
	        //get customer's id since reservations doesn't store the username
	        int id = 0;
	        try (PreparedStatement statement1 = connection.prepareStatement("SELECT ID FROM customers WHERE user_username=?")) {
	            statement1.setString(1, username);
	            try (ResultSet rs = statement1.executeQuery()) {
	               if (rs.next()) {
	                    id = rs.getInt("ID");
	                    System.out.println(id);
	               }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        //set the appropriate ids in a Booking object
	        Booking Booking = new Booking();
	        try {
	            PreparedStatement preparedStatement = connection.
	                    prepareStatement("SELECT MAX(ReservationID) AS maxReservationId FROM reservations");
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	int maxReservationId = rs.getInt("maxReservationId") + 1;
	            	Booking.setReservationID(maxReservationId);
	                Booking.setScreeningID(screenID);
	                Booking.setCustomerID(id);
	                Booking.setSeatsBooked(0);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return Booking;
	    }
	 
	//Create a list of Booking objects called CustomerBookings which contains all rows of table reservations that were made by currently logged in customer
	 public List<Booking> getCustomerBookings(String username) {
	        List<Booking> CustomerBookings = new ArrayList<Booking>();
	        //get customer's id since reservations doesn't store the username
	        int id = 0;
	        try (PreparedStatement statement1 = connection.prepareStatement("SELECT ID FROM customers WHERE user_username=?")) {
	            statement1.setString(1, username);
	            try (ResultSet rs = statement1.executeQuery()) {
	               if (rs.next()) {
	                    id = rs.getInt("ID");
	                    System.out.println(id);
	               }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
            //Now make the list of reservations where customer id is current cutomer's id  
	        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations WHERE CUSTOMERS_ID=?")) {
	            statement.setInt(1, id);
	            System.out.println(id);
	            try (ResultSet rs = statement.executeQuery()) {
	                while (rs.next()) {
	                	//create each booking object
	                    Booking booking = new Booking();
	                    booking.setReservationID(rs.getInt("ReservationID"));
	                    booking.setScreeningID(rs.getInt("PROVOLES_Screening_Id"));
	                    booking.setCustomerID(rs.getInt("CUSTOMERS_ID"));
	                    booking.setSeatsBooked(rs.getInt("SEATS_Booked"));
	                    //and store it in CustomerBookings list
	                    CustomerBookings.add(booking);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        //return finished list
	        return CustomerBookings;
		}


}
