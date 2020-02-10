package classes;
public class Notificacao {
    private boolean visualizado;
    private boolean respondido;
    private boolean ok;
    private String titulo;
    private String mensagem;
    private Pessoa destinatario;
    private Pessoa remetente;
    private Double valor;
    private Conta tipo;

    //CONSTRUTOR ********************************************************

    public Notificacao(String titulo, String mensagem, Pessoa destinatario, Pessoa remetente, Conta tipo) {
        this.visualizado = false;
        this.respondido = false;
        this.ok = false;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.valor = null;
        this.tipo = tipo;
    }
    ////// GETTERS E SETTERS ********************************************

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Notificacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Pessoa getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Pessoa destinatario) {
        this.destinatario = destinatario;
    }

    public Pessoa getRemetente() {
        return remetente;
    }

    public void setRemetente(Pessoa remetente) {
        this.remetente = remetente;
    }

    public Conta getTipo() {
        return tipo;
    }

    public void setTipo(Conta tipo) {
        this.tipo = tipo;
    }

}
