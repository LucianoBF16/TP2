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
        if (!(obj instanceof NotaFinal)) return false;
        NotaFinal otra = (NotaFinal) obj;
        return Double.compare(_nota, otra._nota) == 0 && _id == otra._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_nota, _id);
    }

}
