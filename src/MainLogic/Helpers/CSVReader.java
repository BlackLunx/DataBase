package MainLogic.Helpers;

import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    private ArrayList<String[]> output = new ArrayList<>();

    public CSVReader(String path) {
        this.constructor(path, ";");
    }

    public CSVReader(String path, String del) {
        this.constructor(path, del);
    }

    private void constructor(String path, String del) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while((line = br.readLine()) != null) {
                output.add(line.split(del));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getOutput() {
        return output;
    }

}
