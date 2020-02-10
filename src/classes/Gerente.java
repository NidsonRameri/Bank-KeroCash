package classes;

public class Gerente extends Administrador{

    public Gerente(String nome, String telefone, String cpf, String login, String senha) {
        super(nome, telefone, cpf, login, senha, "GERENTE");
    }
    
    ////////////////////////////////////////////////////////////////////////////////
    //TO STRING
    @Override
    public String toString(){
        return getNome();
    }
    
    //////////////////////////////////////////////////////////////////////////
    
}
