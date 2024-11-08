package db;

import java.sql.*;

public class JDBC {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_schema"; //localhost
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "1234";
    public static final String DB_LOGIN_TABLE_NAME = "LOGIN";
    public static final String DB_LEVELS_TABLE_NAME = "LEVELS";
    private static ResultSet resultSpecialSet;


    public static void register(String teamName, String password){
        try {
             if (!isNameExist(teamName)) {
                 Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                 PreparedStatement insertUser = connection.prepareStatement(
                         "INSERT INTO " + DB_LOGIN_TABLE_NAME + "(teamname, password)" +
                                 "VALUES(?, ?)"
                 );
                 insertUser.setString(1, teamName);
                 insertUser.setString(2, password);

                 insertUser.executeUpdate();

                 PreparedStatement insertUser2 = connection.prepareStatement(
                         "INSERT INTO " + DB_LEVELS_TABLE_NAME + "(teamname, current_level)" +
                                 "VALUES(?, ?)"
                 );
                 insertUser2.setString(1, teamName);
                 insertUser2.setInt(2, 1);

                 insertUser2.executeUpdate();
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean isNameExist(String teamName) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + DB_LOGIN_TABLE_NAME + " WHERE TEAMNAME = ?"
            );
            checkUserExists.setString(1, teamName);

            ResultSet resultSet = checkUserExists.executeQuery();
            return resultSet.isBeforeFirst();  // מחזיר true אם קיים רשומה
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean validateLogin(String teamName, String password){
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + DB_LOGIN_TABLE_NAME +
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


    public static void updateLevel(String teamName, int level) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD
            );
            if (isNameExist(teamName)) {
                int currentLevel = showUpdate(teamName);
                if (currentLevel == level - 1){
                    PreparedStatement updateUsersTable = connection.prepareStatement(
                            "UPDATE " + DB_LEVELS_TABLE_NAME + " SET current_level = ? WHERE teamName = ?");

                    updateUsersTable.setString(2, teamName);
                    updateUsersTable.setInt(1, level);

                    updateUsersTable.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static int showUpdate(String teamName) {
        int currentLevel = 1;
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD
            );
            PreparedStatement showTogether = connection.prepareStatement(
                    "SELECT login.teamname, levels.current_level " +
                            "FROM " + DB_LOGIN_TABLE_NAME + " login " +
                            "JOIN " + DB_LEVELS_TABLE_NAME + " levels " +
                            "ON login.teamname = levels.teamname " +
                            "WHERE login.teamname = ?"
            );
            showTogether.setString(1, teamName);

            resultSpecialSet = showTogether.executeQuery();

            // בדיקה אם ה-ResultSet אינו ריק
            if (resultSpecialSet.next()) {
                currentLevel = resultSpecialSet.getInt("current_level");
            } else {
                System.out.println("No results found for team teamName: " + teamName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentLevel;
    }
}
