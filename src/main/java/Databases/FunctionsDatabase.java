package Databases;

import java.sql.*;
import java.util.ArrayList;

public class FunctionsDatabase {
    PreparedStatement equation;
    Connection con;
    Statement stat;


    public FunctionsDatabase(Connection con) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.con = con;
        stat = this.con.createStatement();

        if (!checkIfTableExists("Functions")) {
            stat.execute("CREATE TABLE Functions( Function VARCHAR(255)); ");
        }
        equation = con.prepareStatement("INSERT INTO Functions VALUES (?);");

    }

    public boolean checkIfTableExists(String tableName) throws SQLException {
        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }

    public void insertFunction(String function) throws SQLException {
        equation.setString(1, function);
        equation.execute();
    }

    public ArrayList<String> getAllFunctions() throws SQLException {
        ArrayList<String> output = new ArrayList<>();
        Statement statement = con.createStatement();


        ResultSet results = statement.executeQuery("SELECT * FROM Functions;");

        while (results.next()) {
            output.add(results.getString("Function"));
        }
        return output;
    }
    public void closeCon() throws SQLException {
        con.close();
    }
}
