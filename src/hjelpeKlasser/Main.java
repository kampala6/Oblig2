package hjelpeKlasser;

public class Main {

    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<String>();

        System.out.println(liste.antall() + " " + liste.tom());
    }
}
