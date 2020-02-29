package MainLogic.Helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadColumns {
    public ArrayList<String> columns;
    public ReadColumns() {
        this.read("res/columns.txt");
    }
    public ReadColumns(String path) {
        this.read(path);
    }
    private void read(String path) {
        columns = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while((line = br.readLine()) != null) {
                columns.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

