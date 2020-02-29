package MainLogic;

import GUI.Alert;

import java.io.File;
import java.io.IOException;

public class ButtonsMainMenu {
    public ButtonsMainMenu() {

    }

    public void createDB(String name) throws IOException {
        File file = new File("res/" +name + ".csv");
        if(file.createNewFile()) {
            generateAlert("Файл успешно создан");
        }
        generateAlert("Файл с таким именем уже существует");
    }

    public void openDB(String name) {
        File file = new File("res/");
        if(file.exists()) {
            generateAlert("Такой файл уже существует!");
        }
    }
    private void generateAlert(String message) {
        Alert alert = new Alert(message);
        alert.pack();
        alert.setVisible(true);
    }
}
