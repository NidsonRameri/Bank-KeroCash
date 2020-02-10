package classes;

import exceptions.ErroException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

public class ContaCorrente extends Conta{
    
    private double limiteTotal;
    private double limite;
    
    // CONSTRUTOR ////////////*******************///////////////
    public ContaCorrente(int conta, int agencia, String senha, double saldo) {
        
        super(conta, agencia, senha, saldo);
        this.setTipo("Corrente");
        this.limite = 1000f;
        this.limiteTotal = 1000f;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimiteTotal() {
        return limiteTotal;
    }

    public void setLimiteTotal(double limiteTotal) {
        this.limiteTotal = limiteTotal;
    }
    
    public void pagar(double valor) throws ErroException,InvocationTargetException{
        if(valor <= this.getSaldo()){
            this.setSaldo(getSaldo() - valor);
            JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso! Valor: "+valor+" R$");
        }else{
            Double v = valor;
            if(getSaldo()>0)
            v = valor - getSaldo();
            else
            v = valor;
            
            if(getLimite() - v >= 0){
            setLimite(getLimite() - v);
            this.setSaldo(getSaldo() - valor);
            JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso!");
            }else{
                throw new ErroException("Operação não realizada. Limite atual da conta: "+this.getLimite()+" R$");
            }
        }
    }
    
    @Override
    public void depositar(double valor){
        double novoSaldo = getLimite()+valor;
        
        if(novoSaldo >= getLimiteTotal()){     // Limite fica positivo
            this.setLimite(getLimiteTotal());
            this.setSaldo(novoSaldo - getLimiteTotal()+getSaldo());
        }
        else {
            this.setSaldo(getSaldo()+valor);
            this.setLimite(getLimite()+valor);
        }
    }
    
    
}
