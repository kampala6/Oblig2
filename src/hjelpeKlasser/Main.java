package hjelpeKlasser;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<String>();

        loopit("Oppgave1");
        System.out.println();

        System.out.println(liste.antall() + " " + liste.tom());


        String[] s = {"Ole", null, "Per", null, null, null};
       // Liste<String> listeNavn = new DobbeltLenketListe<>(s);

        loopit("Oppgave2");

        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println();

        System.out.println(l1.toString() + " "+ l2.toString()
                + " "+ l3.toString() + " "+ l1.omvendtString()
                + " "+ l2.omvendtString() + " "+ l3.omvendtString());

        DobbeltLenketListe<Integer> liste1 = new DobbeltLenketListe<>();

        System.out.println(liste1.toString() + ""+ liste1.omvendtString());

        for(int i = 1; i <= 3; i++){
            liste1.leggInn(i);
            System.out.println(liste1.toString() + ""+ liste1.omvendtString());}

        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste3 = new DobbeltLenketListe<>(c);
        System.out.println( liste3.toString());
        System.out.println(liste3.antall());
        System.out.println(liste3.subliste(3,8)); // [D, E, F, G, H]
        System.out.println(liste3.subliste(5,5)); // []
        System.out.println(liste3.subliste(8,liste3.antall())); // [I, J]
       // System.out.println(liste3.subliste(0,11)); // [I, J]
        System.out.println();

        loopit("fjernMethod");

      //  liste3.fjern('0');

        System.out.println(liste3.toString());
        System.out.println();

        loopit("Oppgave 8 with tableListe and EnkeltLenketListe");
        System.out.println();


        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste11 = new DobbeltLenketListe<>(navn);
        Liste<String> liste2 = new TabellListe<>(navn);
        Liste<String> liste33 = new EnkeltLenketListe<>(navn);
        DobbeltLenketListe.sorter(liste1, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste2, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste3, Comparator.naturalOrder());
        System.out.println(liste11); // [Anders, Berit, Bodil, Kari, Lars, Per]
        System.out.println(liste2); // [Anders, Berit, Bodil, Kari, Lars, Per]
        System.out.println(liste33); // [Anders, Berit, Bodil, Kari, Lars, Per]
// Tabellen navn er upåvirket:
        System.out.println(Arrays.toString(navn));

    }

    public static void loopit(String label){
        System.out.println(label);
        for (int i = 0; i < label.length(); i++) {
            System.out.print("-");
        }
    }
}
