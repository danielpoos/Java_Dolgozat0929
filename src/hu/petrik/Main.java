package hu.petrik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static List<Bejegyzes> bejegyList = new ArrayList<>();

    public static void bejegyzes(){
        System.out.println("Hány darab bejegyzést szeretne hozzáadni?");
        int db =s.nextInt();
        for (int i = 0; i < db; i++) {
            System.out.println("Adja meg a szerző nevét: ");
            String szer = s.next();
            System.out.println("Adja meg a tartalmát: ");
            String tart = s.next();
            bejegyList.add(new Bejegyzes(szer,tart));
        }
    }

    public static void main(String[] args) {
        bejegyzes();
    }
}
