import GUI.Alert;
import MainLogic.ButtonsMainMenu;
import MainLogic.Helpers.ReadColumns;


import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ButtonsMainMenu check = new ButtonsMainMenu();
        check.deleteDB("lol");
        System.exit(0);
    }
}
