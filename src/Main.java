import javax.print.DocFlavor;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashSet<Integer> kek = new HashSet<>();
        for(int i = 0; i < 10000000; i++) {
            kek.add(i);
        }
        boolean ans = false;
        for(int j = 0;j < 10000;j++){
            System.out.println(j);
            HashSet<Integer> lol = kek;
            lol.add(j);
            kek = lol;
            ans = lol.contains(1);
        }
        System.out.println(ans);
    }
}
