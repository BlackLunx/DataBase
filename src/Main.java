import GUI.Alert;
import GUI.MainMenu;
import GUI.MainWindow;
import MainLogic.Buttons;
import MainLogic.ButtonsMainMenu;
import MainLogic.DataBase;
import MainLogic.Helpers.ReadColumns;
import javafx.util.Pair;


import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        MainMenu wind = new MainMenu();
        wind.pack();
        wind.setVisible(true);
    }
}
