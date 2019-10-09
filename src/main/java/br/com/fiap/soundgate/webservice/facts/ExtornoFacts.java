package br.com.fiap.soundgate.webservice.facts;

public class ExtornoFacts {
    private String usuarioLogin;
    private String usuarioSenha;
    private int ingressoCd;
    private int eventoCd;

    public ExtornoFacts() {
    }

    public ExtornoFacts(String usuarioLogin, String usuarioSenha, int ingressoCd, int eventoCd) {
        this.usuarioLogin = usuarioLogin;
        this.usuarioSenha = usuarioSenha;
        this.ingressoCd = ingressoCd;
        this.eventoCd = eventoCd;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public int getIngressoCd() {
        return ingressoCd;
    }

    public void setIngressoCd(int ingressoCd) {
        this.ingressoCd = ingressoCd;
    }

    public int getEventoCd() {
        return eventoCd;
    }

    public void setEventoCd(int eventoCd) {
        this.eventoCd = eventoCd;
    }
}
