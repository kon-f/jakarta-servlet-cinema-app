package com.bookings;

public class Booking {
	private int reservationID;
	private int screeningID;
    private int customerID;  
    private int seatsBooked;
    
   //getters and setters of booking object
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public int getScreeningID() {
		return screeningID;
	}
	public void setScreeningID(int screeningID) {
		this.screeningID = screeningID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
}
