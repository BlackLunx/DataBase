import GUI.Alert;
import MainLogic.Buttons;
import MainLogic.ButtonsMainMenu;
import MainLogic.DataBase;
import MainLogic.Helpers.ReadColumns;
import javafx.util.Pair;


import javax.print.DocFlavor;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DataBase db = new DataBase("www", new Point(400, 400));
        db.find("brand", "qwewq");

    }
}
