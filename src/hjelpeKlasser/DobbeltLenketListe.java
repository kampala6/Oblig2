package hjelpeKlasser;

import hjelpeKlasser.Liste;

import java.util.*;

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
        if(indeks <= antall / 2){ // letingen etter noden start fra hode mot høyre
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

        for (int i = 0; i < a.length; i++) {
            T verdi = a[i];
            if (verdi != null) {
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
               txt.append(", " + peker.neste.verdi);
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
               txt.append(", " + peker.forrige.verdi);
               peker = peker.forrige;
           }
        }
        txt.append(']');
        return txt.toString();

    }
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        for (int i = liste.antall(); i > 0 ; i--) {
            Iterator<T> iterator = liste.iterator();
            int min = 0;
            T minverdi = iterator.next();
            for (int j = 1; j < i; j++) {
                T tempverdie = iterator.next();
                if(c.compare(tempverdie, minverdi) < 0){
                    min = i;
                    minverdi = tempverdie;
                }
            }liste.leggInn(liste.fjern(min));
        }
    }

    /**
     * Method fra avsnitt 1.2.2
     * @param antall
     * @param fra
     * @param til
     */
    public static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new java.lang.IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new java.lang.IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

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

        int i = fra;
        while (i < til) {
            liste.leggInn(peker.verdi);
            peker = peker.neste;
            i++;
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

        if(antall == 0){
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

        if (verdi == null) throw
                new NullPointerException("Ikke tillatt med null-verdier!");
        indeksKontroll(indeks, true);

        if(antall == 0){
            hode = hale = new Node<>(verdi, null, null);
        }else if(indeks == 0){
            hode = hode.forrige = new Node<>(verdi, null, hode);
        }else if(indeks == antall){
            hale = hale.neste =new Node<>(verdi, hale, null);

        }else {
            Node<T> peker = finnNode(indeks);
            peker.forrige = peker.forrige.neste = new Node<>(verdi, peker.forrige, peker);
        }
        antall++;
        endringer++;

    }

    /**
     * Bruked method fra Løsningsforslag oppgave i avsnitt 3.3.3
     * @param verdi
     * @return
     */
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    /**
     * Oppgave 3a
     * vi bruke method finnNode som en hjelp method til å
     * finne noden som har engitt posisjon/indeks(Fra kompendium avsnitt 3.3.3(Klassens øvrige metoder))
     * @param indeks
     * @return
     */
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false); //false: indeks = antall er ulovilg
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null) return -1;
        Node<T> startNode = hode;
        int index  = -1;
         for (int i = 0; i < antall; i++){
            if (startNode.verdi.equals(verdi)){
                index = i;
                break;
            }
            startNode = startNode.neste;
        }
        return index;
    }

    @Override
    public T oppdater(int indeks, T verdi) {

        Objects.requireNonNull(verdi, "Null verdi ikke lov");
        indeksKontroll(indeks, false); //false: indeks = antall er ulovilg

        Node<T> nodepeker = finnNode(indeks); //her finner vi index først so skal gir det verdi vi skal oppdater

        T oldvalue = nodepeker.verdi;  //Lagger verdi
        nodepeker.verdi = verdi;
        endringer++;

        return oldvalue;
    }

    @Override
    public boolean fjern(T verdi) {

        if(verdi == null) return false;

        Node<T> tempNode = hode; // pointer is at the head or start

        while (tempNode != null){
            if(tempNode.verdi.equals(verdi)) break;
           tempNode = tempNode.neste;
        }
        if (tempNode == null) {
            return false;
        }
        else if (antall == 1){  //one node in the list then the head is also the tail
                hode = hale = null;
        } else if(tempNode == hode){
            (hode = hode.neste).forrige = null;
        }else if(tempNode == hale){
            (hale = hale.forrige).neste = null;

        }else {
            (tempNode.forrige.neste = tempNode.neste).forrige = tempNode.forrige;
        }

        tempNode.verdi = null;
        tempNode.forrige = tempNode.neste = null;
        antall--;
        endringer++;


        return true;
    }

    @Override
    public T fjern(int indeks) {

        //indeksKontroll(indeks, true);
        if(indeks < 0){
            throw  new IndexOutOfBoundsException("Index er Ulovlig");
        }else if(indeks >= antall){
            throw new IndexOutOfBoundsException("Index er ut av tabellen");
        }

        Node<T> tempNode = hode;

        if(antall == 1){
            hode = hale = null;
        }else if(indeks == 0){
            (hode = hode.neste).forrige = null;
        }else if(indeks == antall - 1){ //when we come to the end
            tempNode = hale;
            (hale = hale.forrige).neste = null;
        }else {
            //here we find the index that should be removed or delected
            tempNode = finnNode(indeks);
            tempNode.forrige.neste = tempNode.neste;
            tempNode.neste.forrige = tempNode.forrige;
        }

        T tempverdi = tempNode.verdi;
        tempNode.verdi = null;
        tempNode.forrige = tempNode.neste = null;

        antall--;
        endringer++;

        return tempverdi;
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

    /**
     * Oppgave 7
     * referanser skal null still: forrige,neste og verdi
     */
    @Override
    public void nullstill() {

        Node<T> startNodePointer = hode;
        while (startNodePointer != null){
            Node<T> tempNode = startNodePointer.neste; // store pointer in tempNode
            startNodePointer.neste = null;
            startNodePointer.forrige = null;
            startNodePointer.verdi = null;

            startNodePointer = tempNode;
            antall = 0;
            endringer++;
            hode = hale = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {

        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            if(indeks < 0){
                throw  new IndexOutOfBoundsException("Index er Ulovlig");
            }else if(indeks >= antall){
                throw new IndexOutOfBoundsException("Indek er ut av tabellen");
            }
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next() {
            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("Liste er endret");
            }
            if(!hasNext()){
                throw new NoSuchElementException("Tomt eller ingen flere verdier!");
            }
            fjernOK = true;
             T verdi = denne.verdi;
             denne = denne.neste;
             return verdi;
        }


        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

    } // DobbeltLenketListeIterator
}