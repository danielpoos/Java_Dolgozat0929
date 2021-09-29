package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            System.out.println("Nem sikerült a beolvasás");
        }
    }
    public static void likesForSale(){
        Random r = new Random();
        for (int i = 0; i < bejegyList.size()*20; i++) bejegyList.get(r.nextInt(bejegyList.size())).like();
    }
    public static void masodikbejmod(){
        bejegyList.get(1).setTartalom(s.next());
    }
    public static int legnepszerubbBejegy(){
        int max=bejegyList.get(0).getLikeok();
        for (int index = 0; index < bejegyList.size(); index++) {
            if (bejegyList.get(index).getLikeok()> max) max= bejegyList.get(index).getLikeok();
        }
        return max;
    }
    public static boolean vanTobbMint35(){
        boolean van =false;
        for (Bejegyzes b:bejegyList) {
            if(b.getLikeok()>35) van=true;
        }
        return van;
    }
    public static void main(String[] args) {
        bejegyzes();
        beOlv("bejegyzesek.txt");
        likesForSale();
        masodikbejmod();
        for (Bejegyzes b:bejegyList) {
            System.out.println(b);
        }
        System.out.print("\nA legnépszerűbb bejegyzés likejainak száma: ");
        legnepszerubbBejegy();
        if (vanTobbMint35()) System.out.println("\nVan több, mint 35 like egyetlen bejegyzésen.");
        else System.out.println("Nincsen olyan bejegyzés amin több, mint 35 like van");

    }
}
