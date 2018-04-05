package Databases;

import java.sql.*;
import java.util.*;

public class HistoryDatabase {
    PreparedStatement equation;
    Connection con;
    Statement stat;
    public HistoryDatabase() throws SQLException, ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:equationsdb");
        stat = con.createStatement();
        if(!checkIfTableExists("History")) {
            stat.execute("CREATE TABLE History( InputString VARCHAR(255), TimeEntered TIMESTAMP ); ");
        }
        equation = con.prepareStatement("INSERT INTO History VALUES (?, CURRENT_TIMESTAMP);");
    }
    public void insertEquation(String equationSting) throws SQLException {
        equation.setString(1, equationSting);
        equation.execute();
    }
    private ResultSet getAllEquationsSet(boolean timestamp) throws SQLException {
        Statement statement = con.createStatement();
        if(timestamp){
            return statement.executeQuery("SELECT * FROM History;");
        }else{
            return statement.executeQuery("SELECT InputString FROM History;");
        }
    }
    private boolean checkIfTableExists(String tableName) throws SQLException {
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
    public Map<String, String> getAllTimeStamps() throws SQLException {
        Map<String, String> output = new HashMap<>();
        ResultSet results = this.getAllEquationsSet(true);
        while(results.next()){
            //Why is this a String?????? Should be a timestamp. If you replace this with getTimestamp it breaks.

            String gottenTimestamp = results.getString("TimeEntered");

            output.put(gottenTimestamp,results.getString("InputString"));
        }
        return output;
    }
    public Connection getCon() {
        return con;
    }
    public void closeCon() throws SQLException {
        con.close();
    }
    public void dropEverything() throws SQLException{
        equation = con.prepareStatement("DELETE FROM History");
        equation.execute();
    }

}
