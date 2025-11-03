package com.screenings;

public class Screening {
    private int screeningID;
    private int movieID;
    private String movieName;
    private int cinemaID;
    private int contentAdminID;
    private String date;
    private int seatsAvailable;
	private String time;
	
	//getters and setters of Screening object + a toString for system display
	public int getScreeningID() {
		return screeningID;
	}
	public void setScreeningID(int screeningID) {
		this.screeningID = screeningID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	public int getContentAdminID() {
		return contentAdminID;
	}
	public void setContentAdminID(int contentAdminID) {
		this.contentAdminID = contentAdminID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
   @Override
	public String toString() {
		return "Screening [Screening ID=" + screeningID + "Movie ID=" + movieID + ", Name=" + movieName
	                + ", Cinema ID=" + cinemaID +  ", Content Admin ID=" + contentAdminID 
	                + ", Date=" + date + ", Available Seats=" + seatsAvailable + ", Sreening Time=" + time + "]";
	
	
	}
}
