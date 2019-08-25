package br.com.fiap.soundgate.entity;


import java.io.Serializable;

@SuppressWarnings("serial")
public class HistoricoPK implements Serializable{
    private int cd;
    private int usuario;

    public HistoricoPK() {
        super();
    }
    public HistoricoPK(int cd, int usuario) {
        super();
        this.cd = cd;
        this.usuario = usuario;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cd;
        result = prime * result + usuario;
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
        HistoricoPK other = (HistoricoPK) obj;
        if (cd != other.cd)
            return false;
        if (usuario != other.usuario)
            return false;
        return true;
    }

}