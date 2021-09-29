package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public static void beOlv(String path){
        try {
            FileReader f = new FileReader(path);
            BufferedReader b = new BufferedReader(f);
            String sor = b.readLine();
            while (sor!= null){
                String[] data = sor.split(";");
                bejegyList.add(new Bejegyzes(data[0],data[1]));
                sor = b.readLine();
            }
            b.close();
            f.close();
        }
        catch (IOException e){

        }
    }
    public static void main(String[] args) {
        bejegyzes();
        beOlv("bejegyzesek.txt");
    }
}
