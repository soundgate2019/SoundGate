package br.com.fiap.soundgate.webservice.facts;

import br.com.fiap.soundgate.entity.Evento;
import br.com.fiap.soundgate.entity.Usuario;

import java.util.Calendar;

public class IngressoFacts {
    private Usuario usuario;
    private Evento evento;
    private Calendar data;

    public IngressoFacts() {
    }

    public IngressoFacts(Usuario usuario, Evento evento, Calendar data) {
        this.usuario = usuario;
        this.evento = evento;
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
}
