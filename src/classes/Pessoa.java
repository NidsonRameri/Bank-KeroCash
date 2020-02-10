package classes;
public abstract class Pessoa {
    
    ///// Atributos
    private String nome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private int id;

    ////// Construtor
    public Pessoa(String nome, String telefone, String cpf, String login, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        Banco.id++;
        this.id = Banco.id;
    }
   
    ///// Get e Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
