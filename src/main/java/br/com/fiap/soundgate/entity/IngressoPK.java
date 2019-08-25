package br.com.fiap.soundgate.entity;
import java.io.Serializable;

@SuppressWarnings("serial")
public class IngressoPK implements Serializable {
    private int cd;
    private int evento;

    public IngressoPK() {
        super();
    }
    public IngressoPK(int cd, int evento) {
        super();
        this.cd = cd;
        this.evento = evento;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public int getEvento() {
        return evento;
    }
    public void setEvento(int evento) {
        this.evento = evento;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cd;
        result = prime * result + evento;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IngressoPK other = (IngressoPK) obj;
        if (cd != other.cd)
            return false;
        if (evento != other.evento)
            return false;
        return true;
    }

}