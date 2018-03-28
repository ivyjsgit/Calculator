package Databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EquationsDatabase extends Database{
    PreparedStatement equation;
    Connection con;
    Statement stat;
    public EquationsDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:equationsdb");
        stat = con.createStatement();

        if(!checkIfTableExists("Equations")) {
            stat.execute("CREATE TABLE Equations( InputString VARCHAR(255), TimeEntered TIMESTAMP ); ");
        }
        equation = con.prepareStatement("INSERT INTO Equations VALUES (?, CURRENT_TIMESTAMP);");

    }
    public void insertEquation(String equationSting) throws SQLException {
        equation.setString(1, equationSting);
        equation.execute();
    }
    private ResultSet getAllEquationsSet(boolean timestamp) throws SQLException {
        Statement statement = con.createStatement();
        if(timestamp){
            return statement.executeQuery("SELECT * FROM Equations;");
        }else{
            return statement.executeQuery("SELECT InputString FROM Equations;");
        }
    }
    public boolean checkIfTableExists(String tableName) throws SQLException {
        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }
    public ArrayList<String> getAllEquations() throws SQLException {
      ArrayList<String> output = new ArrayList<>();
       ResultSet results = this.getAllEquationsSet(false);
       while(results.next()){
           output.add(results.getString("InputString"));
       }
       return output;
    }
    public HashMap<String, Timestamp> getAllTimeStamps() throws SQLException {
        HashMap<String, Timestamp> output = new HashMap<>();
        ResultSet results = this.getAllEquationsSet(true);
        while(results.next()){
            output.put(results.getString("InputString"),results.getTimestamp("TimeEntered"));
        }
        return output;
    }
}
