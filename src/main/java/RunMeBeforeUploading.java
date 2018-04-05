import DataStructures.InputContainers.DatabaseContainer;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class RunMeBeforeUploading {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HistoryDatabase historyDatabase = new HistoryDatabase();
        FunctionsDatabase functionsDatabase = new FunctionsDatabase(historyDatabase.getCon());
        DatabaseContainer databaseContainer = new DatabaseContainer(historyDatabase,functionsDatabase);
        databaseContainer.dropEverything();

        ArrayList<String> functions = new ArrayList<>();
        functions.add("areasquare s = Math.pow( s , 2 )");

        for(String s:functions){
            databaseContainer.addFunction(s);
        }
    }
}
