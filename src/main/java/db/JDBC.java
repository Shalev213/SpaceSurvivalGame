package db;

import java.sql.*;

public class JDBC {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_schema";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "s1h2a3le5v6.";
    public static final String DB_USERS_TABLE_NAME = "USERS";


    public static boolean register(String username, String password){
        try {
             if (!isExist(username)) {
                 Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                 PreparedStatement insertUser = connection.prepareStatement(
                         "INSERT INTO " + DB_USERS_TABLE_NAME + "(username, password)" +
                                 "VALUES(?, ?)"
                 );

                 insertUser.setString(1, username);
                 insertUser.setString(2, password);

                 insertUser.executeUpdate();
                 return  true;
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean isExist(String username){
        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );
            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
