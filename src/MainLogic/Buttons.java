package MainLogic;

import MainLogic.Helpers.ReadColumns;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

public class Buttons {
    private HashMap<String, Integer> convertColumns;
    public Generator generator;
    public ArrayList<String> columns;
    public HashSet<Integer> alreadyDeleted;
    private String name;
    private String path = "res/bases/";
    private String format = ".csv";
    public Buttons(String name, int from) {
        this.name = name;
        convertColumns = new HashMap<>();
        generator = new Generator(name, from);
        ReadColumns reader = new ReadColumns();
        columns = new ArrayList<>();
        columns.addAll(reader.columns);
        for(int i = 0; i < columns.size(); i++) {
            convertColumns.put(columns.get(i), i);
        }
        alreadyDeleted = new HashSet<>();
    }
    public ArrayList<String[]> find(String columnName, String field) {
        ArrayList<String[]> current = new ArrayList<>();
        if(generator.getObject().get(convertColumns.get(columnName)).get(field) == null && field.length() != 0) {
            return null;
        }
        HashSet<Integer> currentIndexes;
        if(field.length() == 0) {
            currentIndexes = new HashSet<>();
            for(int i = 1;i < generator.getBase().size();i++) {
                currentIndexes.add(i);
            }
        }
        else{
            currentIndexes = new HashSet<>(generator.getObject().get(convertColumns.get(columnName)).get(field));
        }
        currentIndexes.removeAll(alreadyDeleted);
        for(int ind: currentIndexes) {
            current.add(generator.getBase().get(ind));
        }
        return current;
    }

    public Pair<HashSet<Integer>, ArrayList<String[]>> delete(String columnName, String field) {
        if(generator.getObject().get(convertColumns.get(columnName)).get(field) == null) {
            return null;
        }
        HashSet<Integer> currentIndexes = new HashSet<>(generator.getObject().get(convertColumns.get(columnName)).get(field));
        currentIndexes.removeAll(alreadyDeleted);
        return new Pair<>(currentIndexes, generator.getBase());
    }

    public boolean add(@NotNull String[] fields) {
        ArrayList<String[]> check = find("id", fields[0]);
        if(check == null || check.size() == 0) {
            generator.getBase().add(fields);
            for(int i = 0; i < fields.length; i++) {
                if(!generator.getObject().get(i).containsKey(fields[i])) {
                    generator.getObject().get(i).put(fields[i], new HashSet<>());
                }
                HashSet<Integer> currentSet = generator.getObject().get(i).get(fields[i]);
                currentSet.add(generator.getBase().size() - 1);
                generator.getObject().get(i).put(fields[i], currentSet);
            }
            return true;
        }
        return false;
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(path + name + format);
        for(int i = 0; i < generator.getBase().size();i++) {
            if(alreadyDeleted.contains(i)) continue;
            ReadColumns reader = new ReadColumns();
            StringJoiner sj = new StringJoiner(";");
            for(String field: generator.getBase().get(i)) {
                sj.add(field);
            }
            writer.write(sj.toString() + "\n");
        }
        writer.close();
    }
}
