package com.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.util.DBConnection;

public class LoginDao {
    public String authenticateUser(LoginBean loginBean) { 
        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();
        String roleDB = "";
        String saltDB = "";
        
        //try with resources statement, will ensure connection, statement and ResultSet are closed!!
        try (Connection con = DBConnection.createConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM cinema_last.user")) {
            while (resultSet.next()) {
                String userNameDB = resultSet.getString("username"); //Get values from database
                String passwordDB = resultSet.getString("password");
                saltDB = resultSet.getString("salt");
                roleDB = resultSet.getString("role");
                String pass =  Encryptions.getHashMD5(password,saltDB.toString());   //find current given password's hash
                
                //Return appropriate role
                if (userName.equals(userNameDB) && pass.equals(passwordDB) && roleDB.equals("Admin")) {
                    return "Admin_Role";
                } else if (userName.equals(userNameDB) && pass.equals(passwordDB) && roleDB.equals("Content Admin")) {
                    return "Content_Admin_Role";
                } else if (userName.equals(userNameDB) && pass.equals(passwordDB) && roleDB.equals("Customer")) {
                    return "Customer_Role";
                }
            }
        } catch (SQLException e) {  //connection was not established with the database
            e.printStackTrace();
        }
        return "Invalid user credentials";  //user was not found in database
    }
}
