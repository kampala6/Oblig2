package hjelpeKlasser;

import hjelpeKlasser.Liste;

import java.util.Iterator;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T>{

    public static final class Node<T>{

        private T verdi;
        private Node<T> nestepeker, forrigepeker;

        public Node(T verdi, Node<T> nestepeker, Node<T> forrigepeker) {
            this.verdi = verdi;
            this.nestepeker = nestepeker;
            this.forrigepeker = forrigepeker;
        }

        private Node(T verdi){
            this(verdi,null, null);
        }

    }


 //instansvariables
    private Node<T> hode, hale;
    private int antall;
    private int endringer;


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
                hode = hale = new Node<>(verdi, hale, null);
                antall++;
            }
        }
        if(a.length == 0){
            hode = hale = null;// hvis tomt skall de være null
        }else{
            hode = hode.nestepeker.forrigepeker = null; //Spesielt skal både hode.forrigeoghale.nestevære null.
        }
    }


    /**
     * Oppgave 2
     * @return
     */
    @Override
    public String toString(){
        if(tom()) return "[]";
        StringBuilder txt = new StringBuilder();

        Node<T> pointer = hode;
        txt.append(pointer.verdi);
        pointer = pointer.nestepeker;

        while (pointer != null){
            txt.append(",").append("").append(pointer.verdi);
            pointer = pointer.nestepeker;
        }
        txt.append("]");
        return txt.toString();
    }
//    public String toString(){
//        StringBuilder txt = new StringBuilder();
//
//        txt.append("[");
//        Node<T> pointer = hode;
//        if(pointer != null) txt.append(pointer.verdi);
//       while (pointer != null && pointer.nestepeker != null){
//           txt.append(" ," + pointer.nestepeker.verdi);
//           pointer = pointer.nestepeker;
//       }
//       txt.append("]");
//       return txt.toString();
//
//    }

    /**
     *
     * @return
     */
    public String omvendtString() {

        if(tom()) return "[]";
        StringBuilder txt = new StringBuilder();

        Node<T> pointer = hale;
        txt.append(pointer.verdi);
        pointer = pointer.forrigepeker;

        while (pointer != null){
            txt.append(",").append("").append(pointer.verdi);
            pointer = pointer.forrigepeker;
        }
        txt.append("]");
        return txt.toString();

//        txt.append("[");
//        Node<T> pointer = hale;
//        if(pointer != null) txt.append(pointer.verdi);
//        while (pointer != null && pointer.forrigepeker != null){
//            txt.append(" ," + pointer.forrigepeker.verdi);
//            pointer = pointer.forrigepeker;
//        }
//        txt.append("]");
//        return txt.toString();
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