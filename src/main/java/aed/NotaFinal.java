package aed;

import java.util.Objects;

public class NotaFinal implements Comparable<NotaFinal> {
    public double _nota;
    public int _id;

    public NotaFinal(double nota, int id){
        _nota = nota;
        _id = id;
    }

    @Override
    public int compareTo(NotaFinal otra) {
        int cmp = Double.compare(otra._nota, this._nota); // nota descendente
        if (cmp != 0) return cmp;
        return Integer.compare(otra._id, this._id);       // id descendente
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != NotaFinal.class) return false;
        NotaFinal otra = (NotaFinal) obj;
        return Double.compare(this._nota, otra._nota) == 0 && this._id == otra._id;
    }

}
