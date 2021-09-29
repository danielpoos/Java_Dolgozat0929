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
    static List<Bejegyzes> bejegyzesList = new ArrayList<>();
    static List<Bejegyzes> rendezett = new ArrayList<>();
    public static void bejegyzes(){
        System.out.println("Hány darab bejegyzést szeretne hozzáadni?");
        int db =s.nextInt();
        for (int i = 0; i < db; i++) {
            System.out.println("Adja meg a szerző nevét: ");
            String szer = s.next();
            System.out.println("Adja meg a tartalmát: ");
            String tart = s.next();
            bejegyzesList.add(new Bejegyzes(szer,tart));
        }
    }
    public static void beOlv(String path){
        try {
            FileReader f = new FileReader(path);
            BufferedReader b = new BufferedReader(f);
            String sor = b.readLine();
            while (sor!= null){
                String[] data = sor.split(";");
                bejegyzesList.add(new Bejegyzes(data[0],data[1]));
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
        for (int i = 0; i < bejegyzesList.size()*20; i++) bejegyzesList.get(r.nextInt(bejegyzesList.size())).like();
    }
    public static void masodikbejmod(){
        bejegyzesList.get(1).setTartalom(s.next());
    }
    public static int legnepszerubbBejegy(){
        int max= bejegyzesList.get(0).getLikeok();
        for (Bejegyzes b: bejegyzesList) {
            if (b.getLikeok()> max) max= b.getLikeok();
        }
        return max;
    }
    public static boolean vanTobbMint35(){
        boolean van =false;
        for (Bejegyzes b: bejegyzesList) {
            if (b.getLikeok() > 35) {
                van = true;
                break;
            }
        }
        return van;
    }
    public static int kevesebbMint15(){
        int index = 0;
        for (Bejegyzes b: bejegyzesList) {
            if (b.getLikeok()<15) index++;
        }
        return index;
    }
    /*public static void rendezettFeltolt(){
        Bejegyzes tarolo;
        rendezett.addAll(bejegyzesList);
        for (int i = 0; i < bejegyzesList.size(); i++) {
            for (int j = bejegyzesList.size(); j>0; j--) {
                //if(rendezett.get(i).getLikeok() < bejegyzesList.get(j).getLikeok()){
                    tarolo = rendezett.get(i);
                    rendezett.set(i, bejegyzesList.get(j));
                    bejegyzesList.set(i,tarolo);
            }
        }
    }*/
    public static void main(String[] args) {
        bejegyzes();
        beOlv("bejegyzesek.txt");
        likesForSale();
        masodikbejmod();
        for (Bejegyzes b: bejegyzesList) {
            System.out.println(b);
        }
        System.out.printf("\nA legnépszerűbb bejegyzés likejainak száma: %d", legnepszerubbBejegy());
        if (vanTobbMint35()) System.out.println("\nVan több, mint 35 like egyetlen bejegyzésen.");
        else System.out.println("Nincsen olyan bejegyzés amin több, mint 35 like van");
        System.out.printf("%d db bejegyzés kapott kevesebb, mint 15 likeot",kevesebbMint15());
        /*rendezettFeltolt();
        for (Bejegyzes b:rendezett) {
            System.out.println(b);
        }*/
    }
}
