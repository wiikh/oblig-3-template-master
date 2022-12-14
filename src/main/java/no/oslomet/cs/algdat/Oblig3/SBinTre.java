package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Node<T> p=rot; //Initialiserer p.
        Node<T> q=null; //Initialiserer q.
        int c=0;  //Hjelpevariabel.

        while(p!=null){  //Sammenligner og flytter p ut av treet.
            q=p;
            c=comp.compare(verdi, p.verdi);
            p=c<0?p.venstre:p.høyre;
        }

        p=new Node<>(verdi, q);  //Oppretter ny node for p

        if(q==null){  //
            rot=p;
        } else if(c<0){
            q.venstre=p;
        } else{
            q.høyre=p;
        }
        antall++;
        return true;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> p=rot;
        int antallVerdi=0;

         while (p!=null) {
             int c=comp.compare(verdi, p.verdi);
             if (c<0) {
                 p=p.venstre;
             }else{
                 if (c==0) {
                     antallVerdi++;
                 }
                 p=p.høyre;
             }
         }
         return antallVerdi;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        Objects.requireNonNull(p);  //p kan ikke være null
        while(true){  //
            if(p.venstre!=null){
                p=p.venstre;
            }else if(p.høyre!=null){
                p=p.høyre;
            }else{
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node<T> f=p.forelder; //IInitialiserer f til p.forelder.

        if(f==null){
            return null;
        }
        if(f.høyre==p || f.høyre==null){
            return f;
        }else{
            return førstePostorden(f.høyre);
        }
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p=rot;
        Node<T> første=førstePostorden(p);
        oppgave.utførOppgave(første.verdi);

        while(første.forelder!=null){
            første=nestePostorden(første);
            oppgave.utførOppgave(Objects.requireNonNull(første).verdi);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p==null){return;}
        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayList<T> subTreeArray = new ArrayList<>();  //Initialiserer array hvor verdier legges inn.
        Queue<Node<T>> queue = new LinkedList<>(); //Initialiserer kø.
        queue.add(rot);

        while(!queue.isEmpty()){  //Mens treet ikke er tomt.
            Node<T> p=queue.remove();
            subTreeArray.add(p.verdi);

            if(p.venstre!=null){
                queue.add(p.venstre);
            }
            if(p.høyre!=null){
                queue.add(p.høyre);
            }
        }
        return subTreeArray;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tre=new SBinTre<>(c);  //Initialiserer binærtre.

        for(K verdi:data){  //Legger inn verdier i treet for hver ny verdi.
            tre.leggInn(verdi);
        }
        return tre;
    }


} // ObligSBinTre
