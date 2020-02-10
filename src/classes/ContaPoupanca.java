package classes;
public class ContaPoupanca extends Conta{
    
    public ContaPoupanca(int conta, int agencia, String senha, double saldo) {
        super(conta, agencia, senha, saldo);
        this.setTipo("Poupança");
    }
    
}
