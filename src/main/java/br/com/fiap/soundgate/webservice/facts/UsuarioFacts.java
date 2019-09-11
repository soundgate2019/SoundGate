package br.com.fiap.soundgate.webservice.facts;

public class UsuarioFacts {
    private String login;
    private String senha;
    public UsuarioFacts(){

    }
    public UsuarioFacts(String login,String senha){
        this.senha=senha;
        this.login=login;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
