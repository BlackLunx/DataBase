package MainLogic;

import GUI.Alert;
import GUI.Delete;
import GUI.Finds;
import MainLogic.Helpers.ReadColumns;
import javafx.util.Pair;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringJoiner;

public class DataBase {
    private Buttons buttons;
    private String name;
    private String path = "res/backups/";
    private String format = ".db";
    private int cnt = 0;
    public Point screen;
    public String[] cols;
    public DataBase(String name, Point screen, int from) {
        this.screen = new Point(screen);
        buttons = new Buttons(name, from);
        this.name = name;
        cols = new String[buttons.columns.size()];
        for(int i = 0;i < buttons.columns.size();i++) {
            cols[i] = buttons.columns.get(i);
        }
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

    public void find(String fieldName, String field) throws IOException {
        ArrayList<String[]> finder = buttons.find(fieldName, field);
        if(finder == null || finder.size() == 0) {
            generateAlert("Таких объектов нет!");
            return;
        }
        check();
        String[][] data = new String[finder.size()][finder.get(0).length];
        for(int i = 0; i < finder.size(); i++) {
            data[i] = finder.get(i);
        }

        Finds dialog = new Finds(data, cols);
        dialog.pack();
        dialog.setVisible(true);
    }
    public void delete(String fieldName, String field) throws IOException {
        Pair<HashSet<Integer>, ArrayList<String[]>> deleted = buttons.delete(fieldName, field);
        if(deleted == null) {
            generateAlert("Ничего не найдено для удаления");
            return;
        }
        check();
        String[][] data = new String[deleted.getKey().size()][deleted.getValue().get(0).length];
        int j = 0;
        for(int i = 0; i < deleted.getValue().size(); i++) {
            if (deleted.getKey().contains(i))
                data[j++] = deleted.getValue().get(i);
        }
        Delete dialog = new Delete(data, cols, deleted.getKey(), buttons.alreadyDeleted);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void add(String[] fields) throws IOException {
        check();
        if(buttons.add(fields)) {
            generateAlert("Строка успешно добавлена");
            return;
        }
        generateAlert("Строка с id = " + fields[0] + ", уже существует");
    }
    public void save() throws IOException {
        buttons.save();
    }
    public void generateAlert(String message) {
        Alert alert = new Alert(message, screen);
        alert.pack();
        alert.setVisible(true);
    }

}
