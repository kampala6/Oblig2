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
     * Oppgave 2
     * @return
     */
    public String toString(){

        StringBuilder txt = new StringBuilder();

        txt.append("[");

        Node<T> peker = hode;  // start from the first one
        if(peker != null) {    // if not null start printin the values
           txt.append(peker.verdi);
           while (peker != null && peker.neste != null){
               txt.append("," + peker.neste.verdi);
               peker = peker.neste;
            }
        }
            txt.append(']');
        return txt.toString();

    }

    /**
     *
     * @return
     */
    public String omvendtString() {

        StringBuilder txt = new StringBuilder();

        txt.append("[");

        Node<T> peker = hale;
        if(peker != null) {
           txt.append(peker.verdi);
           while (peker != null && peker.forrige != null){
               txt.append("," + peker.forrige.verdi);
               peker = peker.forrige;
           }
        }
        txt.append(']');
        return txt.toString();

    }

    @Override
    public boolean leggInn(T verdi) {
        return false;
    }

    @Override
    public void leggInn(int indeks, T verdi) {

    }

    @Override
    public boolean inneholder(T verdi) {
        return false;
    }

    @Override
    public T hent(int indeks) {
        return null;
    }

    @Override
    public int indeksTil(T verdi) {
        return 0;
    }

    @Override
    public T oppdater(int indeks, T verdi) {
        return null;
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