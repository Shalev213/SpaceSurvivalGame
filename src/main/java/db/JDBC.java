package db;

import java.sql.*;

public class JDBC {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_schema";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "s1h2a3le5v6.";
    public static final String DB_USERS_TABLE_NAME = "USERS";


    public static void register(String teamName, String password){
        try {
             if (isNameExist(teamName)) {
                 Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                 PreparedStatement insertUser = connection.prepareStatement(
                         "INSERT INTO " + DB_USERS_TABLE_NAME + "(TEAMNAME, password)" +
                                 "VALUES(?, ?)"
                 );

                 insertUser.setString(1, teamName);
                 insertUser.setString(2, password);

                 insertUser.executeUpdate();
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNameExist(String teamName){
        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + DB_USERS_TABLE_NAME +
                            " WHERE TEAMNAME = ?"
            );
            checkUserExists.setString(1, teamName);

            ResultSet resultSet = checkUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);        }
        return false;
    }

    public static boolean validateLogin(String teamName, String password){
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + DB_USERS_TABLE_NAME +
                            " WHERE TEAMNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1, teamName);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);        }
        return true;
    }
}
