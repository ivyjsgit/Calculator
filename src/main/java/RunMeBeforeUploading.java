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
        functions.add("slope x1 x2 y1 y2 = (y2-y1)/(x2-x1)");
        functions.add("quadraticPlus a b c = ((-b) + (Math.sqrt(Math.pow(b, 2) - 4 * a * c) ) / (2 * a)"); //
        functions.add("quadraticMinus a b c = ((-b) - (Math.sqrt(Math.pow(b, 2) - 4 * a * c) ) / (2 * a)"); //
        functions.add("pythogorenTheorem a b = Math.pow(a, 2) + Math.pow(b, 2)"); //
        functions.add("areaOfRectangle a b = a * b");
        functions.add("volumeOfRectangle a b c = a * b * c");
        functions.add("areaOfTriangle a b = (a * b) / 2");
        functions.add("areaOfCircle r = Math.PI * Math.pow(r, 2)");
        functions.add("volumeOfSphere r = (Math.PI * (Math.pow(r, 3)) * (4/3)"); //
        functions.add("volumeOfCylinder h r = (Math.PI * (Math.pow(r, 2)) * h"); //
        functions.add("volumeOfCone r h = (Math.PI * Math.pow(r, 2) * h) / 3");
        
        

        for(String s:functions){
            databaseContainer.addFunction(s);
        }
    }
}