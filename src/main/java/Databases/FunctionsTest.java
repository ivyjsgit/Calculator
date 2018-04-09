package Databases;

import DataStructures.InputContainers.DatabaseContainer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class FunctionsTest {
    HistoryDatabase historyDatabase = new HistoryDatabase();
    FunctionsDatabase functionsDatabase = new FunctionsDatabase(historyDatabase.getCon());
    DatabaseContainer databaseContainer = new DatabaseContainer(historyDatabase,functionsDatabase);

    public FunctionsTest() throws SQLException, ClassNotFoundException {
    }

    @Test
    public void testAddFunction() throws SQLException {
        databaseContainer.addFunction("test a = a");
        Assert.assertTrue(databaseContainer.getAllFunctions().contains("test a = a"));
        databaseContainer.addEquation("test a = a");
        Assert.assertTrue(databaseContainer.checkEquationInDatabase("test a = a"));

        databaseContainer.dropEverything();
    }

}
