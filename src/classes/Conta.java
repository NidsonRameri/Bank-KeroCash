package classes;

import exceptions.ErroException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

public abstract class Conta {
    private int conta;
    private int agencia;
    private double saldo;
    private String senha;
    private String tipo;

    public Conta(int conta, int agencia, String senha, double saldo) {
        this.conta = conta;
        this.agencia = agencia;
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
////////////////////////////////////// METODOS /////////////////////////////////////////////// 
    public void depositar(double valor) {
        this.setSaldo(getSaldo() + valor);
    }
    
    public void transferencia(Conta contaB, Conta conta, Cliente beneficiado, Cliente clienteTransf, Double valor) throws ErroException, InvocationTargetException{
        if(conta.getTipo().equals("Poupança")){
            if(beneficiado == clienteTransf){
                if(conta.getSaldo() - valor >=0){
                conta.setSaldo(getSaldo() - valor);
                contaB.depositar(valor);
                    JOptionPane.showMessageDialog(null, "Transferencia efetuada com sucesso!");
                }
            }else{
                throw new ErroException("Poupança so pode transferir para outras contas do mesmo dono!");
            }
        }else {
            ContaCorrente cc = (ContaCorrente) conta;
            cc.pagar(valor);
            contaB.depositar(valor);
            //JOptionPane.showMessageDialog(null, "Transferencia efetuada com sucesso!");
        }
        }
    
}
