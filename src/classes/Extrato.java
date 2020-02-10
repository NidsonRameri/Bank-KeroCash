package classes;
public class Extrato {
    private String operacao;
    private double valor;
    private String beneficiado;
    private int agenciaB;
    private int contaB;
    private int agenciaTitular;
    private int contaTitular;
    private double saldoAtual;
//////////////////////////////////////////////////////////////////////////////////

    public Extrato(String operacao, double valor, String beneficiado, int agenciaB, int contaB, int agenciaTitular, int contaTitular, double saldoAtual) {
        this.operacao = operacao;
        this.valor = valor;
        this.beneficiado = beneficiado;
        this.agenciaB = agenciaB;
        this.contaB = contaB;
        this.agenciaTitular = agenciaTitular;
        this.contaTitular = contaTitular;
        this.saldoAtual = saldoAtual;
    }

   

    
    
/////////////////////////////////////////////////////////////////////////////////
    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(String beneficiado) {
        this.beneficiado = beneficiado;
    }

    public int getAgenciaB() {
        return agenciaB;
    }

    public void setAgenciaB(int agenciaB) {
        this.agenciaB = agenciaB;
    }

    public int getContaB() {
        return contaB;
    }

    public void setContaB(int contaB) {
        this.contaB = contaB;
    }

    public int getAgenciaTitular() {
        return agenciaTitular;
    }

    public void setAgenciaTitular(int agenciaTitular) {
        this.agenciaTitular = agenciaTitular;
    }

    public int getContaTitular() {
        return contaTitular;
    }

    public void setContaTitular(int contaTitular) {
        this.contaTitular = contaTitular;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }
    
    /////////////////////////////////////////////
    
    
}
