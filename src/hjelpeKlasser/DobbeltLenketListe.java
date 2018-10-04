package hjelpeKlasser;

import hjelpeKlasser.Liste;

import java.util.Iterator;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T>{

    private static final class Node<T>   // en indre nodeklasse
    {
        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;   // antall endringer i listen


    // hjelpemetode
    private Node<T> finnNode(int indeks) {

        Node<T> pointer;
        //int i = 0;
        if(indeks < antall / 2){ // letingen etter noden start fra hode mot høyre
            pointer = hode;
            for (int i = 0; i < indeks; i++) {
                pointer = pointer.neste;
            }

        }else {
            pointer = hale;
            for (int i = antall -1 ; i > indeks; i--) {
                pointer = pointer.forrige;
            }
        }
           return pointer;
    }

    public DobbeltLenketListe(){  //standardKonstrucktor

        hode = hale = null;
        antall = 0;
        endringer = 0;
    }


    /**
     * konstruktør
     * Oppgave 1b
     * @param a
     */
    public DobbeltLenketListe(T[] a){
        this();   // alle variabelene er nullet ved bruke av stardardKonstrucktor

        Objects.requireNonNull(a, "Null verdi er Ulovlig");
        hode = hale = new Node<>(null);

        for(T verdi : a){
            if(verdi != null){
                hale = hale.neste = new Node<>(verdi, hale, null);
                antall++;
            }
        }
        if(antall == 0) hode = hale = null;// hvis tomt skall de være null
        else (hode = hode.neste).forrige = null; //Spesielt skal både hode.forrigeoghale.nestevære null.
    }


    /**
     * Oppgave 2a
     * @return
     */
    public String toString(){

        StringBuilder txt = new StringBuilder();

        txt.append("[");

        Node<T> peker = hode;  // start from the first one
        if(peker != null) {    // if not null start printin the values
           txt.append(peker.verdi);
           while (true){
               if (peker == null || peker.neste == null) break;
               txt.append("," + peker.neste.verdi);
               peker = peker.neste;
            }
        }
            txt.append(']');
        return txt.toString();

    }

    /**
     *Oppgave 2a
     * @return
     */
    public String omvendtString() {
        StringBuilder txt = new StringBuilder();
        txt.append("[");
        Node<T> peker = hale;
        if(peker != null) {
           txt.append(peker.verdi);
           while (true){
               if (peker == null || peker.forrige == null) break;
               txt.append("," + peker.forrige.verdi);
               peker = peker.forrige;
           }
        }
        txt.append(']');
        return txt.toString();

    }

    /**
     * Method fra avsnitt 1.2.2
     * @param antall
     * @param fra
     * @param til
     */
    public static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    /**
     *
     * @param fra
     * @param til
     * @return
     */
    public Liste<T> subliste(int fra, int til) {
         fratilKontroll(antall , fra, til);
        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();

        Node<T> peker = finnNode(fra); //bruke den til å finne den interval fra

        for (int i = fra; i < til; i++) {
            liste.leggInn(peker.verdi);
            peker = peker.neste;
        }
        return liste;
    }

    /**
     * Oppgave 2b
     * @param verdi
     * @return
     */
    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Null verdi er uloviig");

        if(tom()){
             hode = hale = new Node<>(verdi, null, null);
        }else {
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        antall++;
        endringer++;
        return true;
    }

    /**
     * Oppgave 5
     * @param indeks
     * @param verdi
     */
    @Override
    public void leggInn(int indeks, T verdi) {

        Objects.requireNonNull(verdi,"Ikke lovlig med null verdier" );

        indeksKontroll(indeks, true);

        if(indeks == 0 && antall == 0){
            hode = hale = new Node<>(verdi, null, null);
        }else if(indeks == 0){
            hode = new Node<>(verdi, null, hode);
        }else if(indeks == antall){
            hale = new Node<>(verdi, hale, null);
            hale.neste.neste = hale;
        }else {
            Node<T> peker = finnNode(indeks);
            peker.forrige = peker.forrige.neste = new Node<>(verdi, peker.forrige, peker);
        }
        antall++;
        endringer++;

    }

    @Override
    public boolean inneholder(T verdi) {
        return false;
    }

    /**
     * Oppgave 3a
     * @param indeks
     * @return
     */
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        return 0;
    }

    @Override
    public T oppdater(int indeks, T verdi) {

        Objects.requireNonNull(verdi, "Null verdi ikke lov");
        indeksKontroll(indeks, false);

        Node<T> nodepeker = finnNode(indeks); //her finner vi index først so skal gir det verdi vi skal oppdater

        T oldvalue = nodepeker.verdi;  //Lagger verdi
        nodepeker.verdi = verdi;
        endringer++;

        return oldvalue;
    }

    @Override
    public boolean fjern(T verdi) {
        return false;
    }

    @Override
    public T fjern(int indeks) {
        return null;
    }

    /**
     * Oppgave 1
     * @return
     */
    @Override
    public int antall() {
        return antall;
    }

    /**
     * Oppgave 1
     * @return
     */
    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public Iterator<T> iterator(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator()
        {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks)
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

    } // DobbeltLenketListeIterator
}