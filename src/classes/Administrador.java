package classes;

import java.util.ArrayList;

public abstract class Administrador extends Pessoa{
    
    private String funcao;
    private ArrayList<Notificacao> listaNotificacoes;

    public Administrador(String nome, String telefone, String cpf, String login, String senha, String funcao) {
        super(nome, telefone, cpf, login, senha);
        this.funcao = funcao;
        this.listaNotificacoes = new ArrayList<>();
    }

    public ArrayList<Notificacao> getListaNotificacoes() {
        return listaNotificacoes;
    }

    public void setListaNotificacoes(ArrayList<Notificacao> listaNotificacoes) {
        this.listaNotificacoes = listaNotificacoes;
    }
    

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void adicionarNotificacao(Notificacao notif) {
        this.listaNotificacoes.add(notif);
    }
    
    public void excluirNotificacao(Notificacao notif) {
        this.listaNotificacoes.remove(notif);
    }
    
   
    public boolean notificacaoEnviada(Cliente cliente){
        for(Notificacao n : listaNotificacoes){
            if(n.getRemetente() == cliente && n.isRespondido()==false){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarNovaMensagem(){
        for(Notificacao e : listaNotificacoes){
            if(e.isVisualizado() == false)
                return true;
        }
        return false;
    }
    
}
