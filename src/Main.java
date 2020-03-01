import GUI.Alert;
import MainLogic.ButtonsMainMenu;
import MainLogic.Helpers.ReadColumns;
import javafx.util.Pair;


import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        HashSet<Integer> a = new HashSet<>();
        for(int i = 6; i < 10; i++) a.add(i);
        HashSet<Integer> b = new HashSet<>();
        for(int i = 0; i < 16; i++) b.add(i);
        Pair<HashSet<Integer>, HashSet<Integer>> w = new Pair<>(b, a);
        //w.getKey().retainAll(w.getValue());
        System.out.println(b);
    }
}
