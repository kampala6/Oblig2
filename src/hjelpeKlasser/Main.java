package hjelpeKlasser;

public class Main {

    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<String>();

        loopit("Oppgave1");
        System.out.println();

        System.out.println(liste.antall() + " " + liste.tom());


        String[] s = {"Ole", null, "Per", null, null, null};
        Liste<String> listeNavn = new DobbeltLenketListe<>(s);

        loopit("Oppgave2");

        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println();
        System.out.println(l1.toString() + " "+ l2.toString()
                + " "+ l3.toString() + " "+ l1.omvendtString()
                + " "+ l2.omvendtString() + " "+ l3.omvendtString());
    }

    public static void loopit(String label){
        System.out.println(label);
        for (int i = 0; i < label.length(); i++) {
            System.out.print("-");
        }
    }
}
