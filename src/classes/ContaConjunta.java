package classes;
public class ContaConjunta extends ContaCorrente {
    
    private Cliente segundoCliente;
    private Cliente primeiroCliente;
    
    public ContaConjunta(int conta, int agencia, String senha, Cliente segundoCliente, Cliente primeiroCliente) {
        super(conta, agencia, senha, 0f);
        this.segundoCliente = segundoCliente;
        this.primeiroCliente = primeiroCliente;
        this.setTipo("Conjunta");
    }

    public Cliente getSegundoCliente() {
        return segundoCliente;
        
    }

    public void setSegundoCliente(Cliente segundoCliente) {
        this.segundoCliente = segundoCliente;
    }

    public Cliente getPrimeiroCliente() {
        return primeiroCliente;
    }

    public void setPrimeiroCliente(Cliente primeiroCliente) {
        this.primeiroCliente = primeiroCliente;
    }
    
    
    
}
