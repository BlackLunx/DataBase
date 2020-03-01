package MainLogic;

import MainLogic.Helpers.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Generator {
    private ArrayList<HashMap<String, HashSet<Integer> > > object;
    private ArrayList<String[]> base;
    private String path = "res/bases/";
    private String format = ".csv";
    public Generator(String name, String del) {
        this.constructor(name, del);
    }

    public Generator(String name) {
        this.constructor(name, ";");
    }


    private void constructor(String name, String del) {
        CSVReader reader = new CSVReader(path + name + format, del);
        base = reader.getOutput();
        object = new ArrayList<>();
        String[] columnNames = base.get(0);
        for(String currentName: columnNames) {
            object.add(new HashMap<>());
        }
        for(int i = 1; i < base.size(); i++) {
            String[] fields = base.get(i);
            for(int j = 0; j < fields.length; j++) {
                if(!object.get(j).containsKey(fields[j])) {
                    object.get(j).put(fields[j], new HashSet<>());
                }
                HashSet<Integer> currentSet = object.get(j).get(fields[j]);
                currentSet.add(i);
                object.get(j).put(fields[j], currentSet);
            }
        }
    }

    public ArrayList<HashMap<String, HashSet<Integer> > > getObject() {
        return object;
    }
    public ArrayList<String[]> getBase() {
        return base;
    }
    public void saveObject() {
        //TO DO
        //CSVSaver.save(object);
    }
}
