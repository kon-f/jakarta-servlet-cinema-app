package com.movies;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

import com.db.util.DBConnection;


import java.util.ArrayList;
import java.util.List;


public class MovieDao {
    private Connection connection;

    public MovieDao() {
        connection = DBConnection.createConnection();
    }
    
    //add a movie. Insert into movies table the object Movie
    public void addMovie (Movie Movie) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO MOVIES (ID, NAME, CONTENT, LENGTH, TYPE, SUMMARY, CONTENT_ADMIN_ID) VALUES (?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Movie.getId());
            preparedStatement.setString(2, Movie.getName());
            preparedStatement.setString(3, Movie.getContent());
            preparedStatement.setInt(4, Movie.getLength());
            preparedStatement.setString(5, Movie.getType());
            preparedStatement.setString(6, Movie.getSummary());
            preparedStatement.setInt(7, Movie.getCadmin_id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Delete movie. Delete from table movies the movie that has ID equal with Id you get on method call
    public void deleteMovie(int Id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from MOVIES where ID=?");
            // Parameters start with 1
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Edit a movie. Update table with values of object "Movie"
    public void updateMovie(Movie Movie) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update movies set NAME=?, CONTENT=?, LENGTH=?, TYPE=?, SUMMARY=?, CONTENT_ADMIN_ID=?  where ID = ?");
            // Parameters start with 1
            
            preparedStatement.setString(1, Movie.getName());
            preparedStatement.setString(2, Movie.getContent());
            preparedStatement.setInt(3, Movie.getLength());
            preparedStatement.setString(4, Movie.getType());
            preparedStatement.setString(5, Movie.getSummary());
            preparedStatement.setInt(6, Movie.getCadmin_id());
			preparedStatement.setInt(7, Movie.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Create a list of Movie objects called Movies which contains all rows of table movies
    public List<Movie> getAllMovies() {
        List<Movie> Movies = new ArrayList<Movie>();
        try {       	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from movies");
            while (rs.next()) {
            	//create each movie object
                Movie Movie = new Movie();
                Movie.setId(rs.getInt("ID"));
                Movie.setName(rs.getString("NAME"));
                Movie.setContent(rs.getString("CONTENT"));
                Movie.setLength(rs.getInt("LENGTH"));
                Movie.setType(rs.getString("TYPE"));
                Movie.setSummary(rs.getString("SUMMARY"));
                Movie.setCadmin_id(rs.getInt("CONTENT_ADMIN_ID"));
                //and add it to the list Movies
                Movies.add(Movie);
                //System.out.println(Movie);  //for debugging purposes
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //finally return the list
        return Movies;
    }
    
    //return a movie object that has a specified ID through method call
    public Movie getMovieByID(int ID) {
        Movie Movie = new Movie();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from movies where ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Movie.setId(rs.getInt("ID"));
                Movie.setName(rs.getString("Name"));
                Movie.setContent(rs.getString("CONTENT"));
                Movie.setLength(rs.getInt("LENGTH"));
                Movie.setType(rs.getString("TYPE"));
                Movie.setSummary(rs.getString("SUMMARY"));
                Movie.setCadmin_id(rs.getInt("CONTENT_ADMIN_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Movie;
    }

}
