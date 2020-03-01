package MainLogic;

import GUI.Alert;
import GUI.Finds;
import MainLogic.Helpers.ReadColumns;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class DataBase {
    private Buttons buttons;
    private String name;
    private String path = "res/backups/";
    private String format = ".csv";
    private int cnt = 0;
    private Point screen;
    public DataBase(String name, Point screen) {
        this.screen = new Point(screen);
        buttons = new Buttons(name);
    }
    private void check() throws IOException {
        cnt++;
        if(cnt % 5 == 0) saveRes();
    }

    private void saveRes() throws IOException {
        FileWriter writer = new FileWriter(path + name + format);
        for(int i = 0; i < buttons.generator.getBase().size();i++) {
            if(buttons.alreadyDeleted.contains(i)) continue;
            ReadColumns reader = new ReadColumns();
            StringJoiner sj = new StringJoiner(";");
            for(String field: buttons.generator.getBase().get(i)) {
                sj.add(field);
            }
            writer.write(sj.toString() + "\n");
        }
        writer.close();
    }

    public void find(String fieldName, String field) {
        ArrayList<String[]> finded = buttons.find(fieldName, field);
        if(finded == null) {
            generateAlert("Таких объектов нет!");
            return;
        }
        String[][] data = new String[finded.size()][finded.get(0).length];
        for(int i = 0; i < finded.size(); i++) {
            data[i] = finded.get(i);
        }
        String[] cols = new String[buttons.columns.size()];
        for(int i = 0;i < buttons.columns.size();i++) {
            cols[i] = buttons.columns.get(i);
        }
        for(String[] t: data) {
            System.out.println(Arrays.toString(t));
        }
        Finds dialog = new Finds(data, cols);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void generateAlert(String message) {
        Alert alert = new Alert(message, screen);
        alert.pack();
        alert.setVisible(true);
    }

}
