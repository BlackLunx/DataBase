package MainLogic;

import GUI.Alert;
import MainLogic.Helpers.ReadColumns;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class ButtonsMainMenu {
    private String path = "res/bases/";
    private String format = ".csv";
    private Point screen;
    public ButtonsMainMenu(Point screen) {
        this.screen = new Point(screen);
    }
    private void generateFile(String name) throws IOException {
        ReadColumns reader = new ReadColumns();
        StringJoiner sj = new StringJoiner(";");
        for(String column: reader.columns) {
            sj.add(column);
        }
        FileWriter writer = new FileWriter(path + name + format);
        writer.write(sj.toString());
        writer.close();
    }
    public void createDB(String name) throws IOException {
        File file = new File(path + name + format);
        if(!file.exists()) {
            generateFile(name);
            generateAlert("Файл успешно создан");
            return;
        }
        generateAlert("Файл с таким именем уже существует");
    }
    public void deleteDB(String name) {
        File file = new File(path + name + format);
        if(file.delete()) {
            generateAlert("Файл успешно удален");
            return;
        }
        generateAlert("Файл с таким именем уже существует");
    }
    public void openDB(String name) {
        File file = new File(path + name + format);
        if(!file.exists()) {
            generateAlert("Такого файла не существует");
            return;
        }

    }
    public void restoreDB(String name) {
        File folder = new File("res/backups/" + name + ".db");
        if(!folder.exists()) {
            generateAlert("Такой базы данных не существует");
            return;
        }

    }
    private void generateAlert(String message) {
        Alert alert = new Alert(message, screen);
        alert.pack();
        alert.setVisible(true);
    }
}
