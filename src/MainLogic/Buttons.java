package MainLogic;

import MainLogic.Helpers.ReadColumns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Buttons {
    private HashMap<String, Integer> convertColumns;
    private Generator generator;
    private ArrayList<String> columns;
    private HashSet<Integer> alreadyDeleted;
    public Buttons() {
        convertColumns = new HashMap<>();
        generator = new Generator();
        ReadColumns reader = new ReadColumns();
        columns = new ArrayList<>();
        columns.addAll(reader.columns);
    }
    public ArrayList<String[]> find(String columnName, String field) {
        ArrayList<String[]> current = new ArrayList<>();
        HashSet<Integer> currentIndexes = new HashSet<>(generator.getObject().get(convertColumns.get(columnName)).get(field));
        currentIndexes.remove(alreadyDeleted);
        for(int ind: currentIndexes) {
            current.add(generator.getBase().get(ind));
        }
        return current;
    }

    public void delete(String columnName, String field) {

    }
}
