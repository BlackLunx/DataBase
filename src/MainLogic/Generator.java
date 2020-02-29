package MainLogic;

import MainLogic.Helpers.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Generator {
    private CSVReader reader = null;
    private ArrayList<HashMap<String, HashSet<Integer> > > object;
    private ArrayList<String[]> base;
    public Generator(String path, String del) {
        this.constructor(path, del);
    }

    public Generator(String path) {
        this.constructor(path, ";");
    }

    public Generator() {
        //TO DO
        // this.constructor("path", ";");
    }

    private void constructor(String path, String del) {
        reader = new CSVReader(path, del);
        base = reader.getOutput();
        String[] columnNames = base.get(0);
        for(String name: columnNames) {
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
