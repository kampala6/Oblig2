import hjelpeKlasser.Liste;

import java.util.Iterator;

public class DobbeltLenketListe<T> implements Liste<T>{

    public static final class Node<T>{

        private T verdi;
        private Node<T> neste, nodebefore;

        public Node(T verdi, Node<T> neste, Node<T> nodebefore) {
            this.verdi = verdi;
            this.neste = neste;
            this.nodebefore = nodebefore;
        }
    }

    public DobbeltLenketListe(){

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
        return 0;
    }

    @Override
    public boolean tom() {
        return false;
    }

    @Override
    public void nullstill() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}