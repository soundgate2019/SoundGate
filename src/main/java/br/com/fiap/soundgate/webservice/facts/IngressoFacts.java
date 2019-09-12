package br.com.fiap.soundgate.webservice.facts;

import br.com.fiap.soundgate.entity.Evento;
import br.com.fiap.soundgate.entity.Usuario;

import java.util.Calendar;

public class IngressoFacts {
    private int usuarioCd;
    private int eventoCd;
    private Calendar data;

    public IngressoFacts() {
    }

    public IngressoFacts(int usuario, int evento, Calendar data) {
        this.usuarioCd = usuario;
        this.eventoCd = evento;
        this.data = data;
    }

    public int getUsuarioCd() {
        return usuarioCd;
    }

    public void setUsuarioCd(int usuario) {
        this.usuarioCd = usuario;
    }

    public int getEventoCd() {
        return eventoCd;
    }

    public void setEventoCd(int evento) {
        this.eventoCd = evento;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
}
