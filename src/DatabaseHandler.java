import java.sql.*;

import javafx.fxml.FXML;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection()
    {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/agodadb";
        String userName = "root";
        String password = "sunnYsidecount25";
        

        try
        {
            connection = DriverManager.getConnection(url, userName, password);

        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }


    // LOGIN VALIDATION
    public static boolean validateAdLog(String username, String password){

        getInstance();
        String query = "SELECT * FROM accounts WHERE Username = '" + username + "' AND Password = '" + password + "'";
        
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateLogin(String email, String password){

        getInstance();
        String query = "SELECT * FROM users WHERE Email = '" + email + "' AND Password = '" + password + "'";
        
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validEmail(String email){

        getInstance();
        String query = "SELECT * FROM users WHERE email = '" + email + "'";
        
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    // GET FROM DATABASE AND IMPORT TO LIST
    public static ResultSet getUsers(){

        ResultSet result = null;

        try {
            String query = "SELECT * FROM users";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet getTrans(){

        ResultSet result = null;

        try {
            String query = "SELECT * FROM transactions";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet getHotels(){

        ResultSet result = null;

        try {
            String query = "SELECT * FROM hotels";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // USERS
    public static boolean addUser(User user) {

        try {
            pstatement = getDBConnection().prepareStatement("INSERT INTO `users` (email, firstName, lastName, phoneNumber, password) VALUES (?,?,?,?,?)");
            pstatement.setString(1, user.getEmail());
            pstatement.setString(2, user.getFirstName());
            pstatement.setString(3, user.getLastName());
            pstatement.setString(4, user.getPhoneNumber());
            pstatement.setString(5, user.getPassword());
            return pstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(User user) {
        try {
            String deleteStatement = "DELETE FROM `users` WHERE Email = ?";
            pstatement = getDBConnection().prepareStatement(deleteStatement);
            pstatement.setString(1, user.getEmail());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean udUser(User user) {
        try {
            String updateStatement = "UPDATE `users` SET custid = ?, email = ?, firstName = ?, lastName = ?, phoneNumber = ?, password = ? WHERE custid = ?";
            pstatement = getDBConnection().prepareStatement(updateStatement);
            pstatement.setString(1, user.getUserId());
            pstatement.setString(2, user.getEmail());
            pstatement.setString(3, user.getFirstName());
            pstatement.setString(4, user.getLastName());
            pstatement.setString(5, user.getPhoneNumber());
            pstatement.setString(6, user.getPassword());
            pstatement.setString(7, user.getUserId());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // PROPERTY
    public static boolean addProperty(Property property) {

        try {
            pstatement = getDBConnection().prepareStatement("INSERT INTO `hotels` (procode, proname, rating, address, protype) VALUES (?,?,?,?,?)");
            pstatement.setString(2, property.getProtypeName());
            pstatement.setString(1, property.getProtypeCode());
            pstatement.setString(4, property.getProtypeAdd());
            pstatement.setString(3, property.getProtypeRating());
            pstatement.setString(5, property.getProType());
            return pstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deletePro(Property property) {
        try {
            String deleteStatement = "DELETE FROM `hotels` WHERE procode = ?";
            pstatement = getDBConnection().prepareStatement(deleteStatement);
            pstatement.setString(1, property.getProtypeCode());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePro(Property property) {
        try {
            String updateStatement = "UPDATE `hotels` SET procode = ?, proname = ?, rating = ?, address = ?, protype = ? WHERE procode = ?";
            pstatement = getDBConnection().prepareStatement(updateStatement);
            pstatement.setString(1, property.getProtypeCode());
            pstatement.setString(2, property.getProtypeName());
            pstatement.setString(3, property.getProtypeRating());
            pstatement.setString(4, property.getProtypeAdd());
            pstatement.setString(5, property.getProType());
            pstatement.setString(6, property.getProtypeCode());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // TRANSACTIONS
    public static boolean addTransaction(Transaction transaction) {

        try {
            pstatement = getDBConnection().prepareStatement("INSERT INTO `transactions` (procode, checkin, checkout, email) VALUES (?,?,?,?)");
            pstatement.setString(2, transaction.getHotelCode());
            pstatement.setString(3, transaction.getCheckIn());
            pstatement.setString(4, transaction.getCheckOut());
            pstatement.setString(5, transaction.getEmail());
            return pstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteTrans(Transaction transaction) {
        try {
            String deleteStatement = "DELETE FROM `transactions` WHERE resNum = ?";
            pstatement = getDBConnection().prepareStatement(deleteStatement);
            pstatement.setString(1, transaction.getResNum());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateTrans(Transaction transaction) { 
        try {
            String updateStatement = "UPDATE `transactions` SET resNum = ?, procode = ?, checkin = ?, checkout = ?, email = ? WHERE resNum = ?";
            pstatement = getDBConnection().prepareStatement(updateStatement);
            pstatement.setString(1, transaction.getResNum());
            pstatement.setString(2, transaction.getHotelCode());
            pstatement.setString(3, transaction.getCheckIn());
            pstatement.setString(4, transaction.getCheckOut());
            pstatement.setString(5, transaction.getEmail());
            pstatement.setString(6, transaction.getResNum());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}