package hjelpeKlasser;

import hjelpeKlasser.Liste;

import java.util.Iterator;

public class DobbeltLenketListe<T> implements Liste<T>{

    public static final class Node<T>{

        private T verdi;
        private Node<T> nestepeker, forrigepeker;

        public Node(T verdi, Node<T> nestepeker, Node<T> forrigepeker) {
            this.verdi = verdi;
            this.nestepeker = nestepeker;
            this.forrigepeker = forrigepeker;
        }
    }


 //instansvariables
    private Node<T> hode, hale;
    private int antall;
    private int antallEndringer;


    public DobbeltLenketListe(){

        hode = hale = null;
        antall = 0;
        antallEndringer = 0;
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

    @Override
    public int antall() {
        return antall;
    }

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
}