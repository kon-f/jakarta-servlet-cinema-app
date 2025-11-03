package com.admins;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.db.util.DBConnection;

public class UserDao {
	
	private Connection connection;

	 public UserDao() {
	     connection = DBConnection.createConnection();
	 }
	 
	 //change the value of the column role into table users where the username is userName into the role the admin servlet specifies
	 public void changeUserRole (String userName, String role) {
	        try {
			    PreparedStatement preparedStatement = connection.prepareStatement ("UPDATE user SET role = ? WHERE username = ?"); 
	            preparedStatement.setString(1, role);
				preparedStatement.setString(2, userName);	
				preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
	 }
	
	 //add user's info into the customers table
	public void addCustomer (User User) {        
		try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO customers (ID, NAME, user_username) VALUES (?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, User.getId());
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//delete user from the customers table
	public void deleteCustomer(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from customers where user_username=?");
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//delete user from content_admin table
	public void deleteCAdmin(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from content_admin where user_username=?");
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//add user into content_admin table
	public void addCAdmin (User User) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO content_admin (ID, NAME, user_username) VALUES (?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, User.getId());
            preparedStatement.setString(2, User.getName());
            preparedStatement.setString(3, User.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//Create a list of all the customers
	public List<User> getAllCustomers() {
        List<User> Customers = new ArrayList<User>();
        try {       	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from customers");
            while (rs.next()) {
            	//create user objects
                User User = new User();
                User.setId(rs.getInt("ID"));
                User.setName(rs.getString("NAME"));
                User.setUsername(rs.getString("user_username"));
                //store each object into Customers list
                Customers.add(User);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the list
        return Customers;
    }
	
	//Create a list of all the customers
	public List<User> getAllCAdmins() {
        List<User> CAdmins = new ArrayList<User>();
        try {       	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from content_admin");
            while (rs.next()) {
            	//create user objects
                User User = new User();
                User.setId(rs.getInt("ID"));
                User.setName(rs.getString("NAME"));
                User.setUsername(rs.getString("user_username"));
                //store each object into CAdmins list
                CAdmins.add(User);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the list
        return CAdmins;
    }

}
