package classes;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private boolean contaPoupancaOk;
    private boolean contaCorrenteOk;
    private boolean contaConjuntaOk;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;
    private ContaConjunta contaConjunta;
    private int agencia;
    private Gerente gerente;
    private ArrayList<Notificacao> listaNotificacoes;
    private ArrayList<Extrato> historico;

    public Cliente(String nome, String telefone, String cpf, String login, String senha, Gerente gerente) {
        super(nome, telefone, cpf, login, senha);
        this.gerente = gerente;
        listaNotificacoes = new ArrayList<>();
        historico = new ArrayList<>();
        this.agencia = (int) (1000 + Math.random() * (10000 - 1000));
        
    }

    public boolean isContaPoupancaOk() {
        return contaPoupancaOk;
    }

    public void setContaPoupancaOk(boolean contaPoupancaOk) {
        this.contaPoupancaOk = contaPoupancaOk;
    }

    public boolean isContaCorrenteOk() {
        return contaCorrenteOk;
    }

    public void setContaCorrenteOk(boolean contaCorrenteOk) {
        this.contaCorrenteOk = contaCorrenteOk;
    }

    public boolean isContaConjuntaOk() {
        return contaConjuntaOk;
    }

    public void setContaConjuntaOk(boolean contaCojuntaOk) {
        this.contaConjuntaOk = contaCojuntaOk;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public ArrayList<Notificacao> getListaNotificacoes() {
        return listaNotificacoes;
    }

    public void setListaNotificacoes(ArrayList<Notificacao> listaNotificacoes) {
        this.listaNotificacoes = listaNotificacoes;
    }

    public ArrayList<Extrato> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Extrato> historico) {
        this.historico = historico;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public ContaConjunta getContaConjunta() {
        return contaConjunta;
    }

    public void setContaConjunta(ContaConjunta contaConjunta) {
        this.contaConjunta = contaConjunta;
    }
////////////////////////////////////////////////////////////////////////////////
    //TO STRING
    @Override
    public String toString(){
        return getNome();
    }
    
    
    /////////////////////////////
    public boolean criarContaCorrente(String senha){
        if(!isContaCorrenteOk()){
        int conta = (int) (1000 + Math.random() * (10000 - 1000));
        String c = "1"+Integer.toString(conta);
        ContaCorrente cc = new ContaCorrente(Integer.parseInt(c), agencia, senha, 0f);
        
        this.setContaCorrente(cc);
        this.setContaCorrenteOk(true);
        return true;
        }
        return false;
    }
        
    public void deletarContaCorrente(){
        setContaCorrente(null);
        setContaCorrenteOk(false);
    }
    public boolean criarContaPoupanca(String senha){
        if(!isContaPoupancaOk()){
        int conta = (int) (1000 + Math.random() * (10000 - 1000));
        String c = "0"+Integer.toString(conta);
        ContaPoupanca cp = new ContaPoupanca(Integer.parseInt(c), agencia, senha, 0f);
        
        this.setContaPoupanca(cp);
        this.setContaPoupancaOk(true);
        return true;
        }
        return false;
    }
    public void deletarContaPoupanca(){
        setContaPoupanca(null);
        setContaPoupancaOk(false);
    }
    public boolean criarContaConjunta(String senha, Cliente segundoCliente, Cliente primeiroCliente){
        if(!isContaConjuntaOk()){
        int conta = (int) (1000 + Math.random() * (10000 - 1000));
        String c = "2"+Integer.toString(conta);
        ContaConjunta cconjunta = new ContaConjunta(Integer.parseInt(c), agencia, senha, segundoCliente, primeiroCliente);
        
        this.setContaConjunta(cconjunta);
        this.setContaConjuntaOk(true);
        return true;
        }
        return false;
    }
    public void deletarContaConjunta(){
        Cliente c = contaConjunta.getSegundoCliente();
        c.setContaConjuntaOk(false);
        c.setContaConjunta(null);
        this.setContaConjuntaOk(false);
        this.setContaConjunta(null);
    }
    
    public void adicionarExtrato(Extrato extrato){
        this.historico.add(extrato);
    }
    
    public void adicionarNotificacao(Notificacao notif) {
        this.listaNotificacoes.add(notif);
    }
    
    public void excluirNotificacao(Notificacao notif) {
        this.listaNotificacoes.remove(notif);
    }
    
    public boolean verificarNovaMensagem(){
        for(Notificacao e : listaNotificacoes){
            if(e.isVisualizado() == false)
                return true;
        }
        return false;
    }
    
}
