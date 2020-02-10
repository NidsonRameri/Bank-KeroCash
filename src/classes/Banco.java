package classes;

import java.util.ArrayList;

public class Banco {
    //////////////////////// Atributos
    public static int id=0;
    public static boolean root;
    public static boolean botaoClientes;
    private static ArrayList<Administrador> listaAdm = new ArrayList();
    private static ArrayList<Cliente> listaClientes = new ArrayList();
    private static Cliente cAtual;
    private static Administrador aAtual;
    private static boolean gerente = false;
    
    /////////////////////// Getters e Setters

    public static boolean isBotaoClientes() {
        return botaoClientes;
    }

    public static void setBotaoClientes(boolean botaoClientes) {
        Banco.botaoClientes = botaoClientes;
    }

    
    
    public static boolean isRoot() {
        return root;
    }

    public static void setRoot(boolean root) {
        Banco.root = root;
    }
    
    public static ArrayList<Administrador> getListaAdm() {
        return listaAdm;
    }

    public static void setListaAdm(ArrayList<Administrador> listaAdm) {
        Banco.listaAdm = listaAdm;
    }

    public static ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static void setListaClientes(ArrayList<Cliente> listaClientes) {
        Banco.listaClientes = listaClientes;
    }

    public static Cliente getcAtual() {
        return cAtual;
    }

    public static void setcAtual(Cliente cAtual) {
        Banco.cAtual = cAtual;
    }

    public static Administrador getaAtual() {
        return aAtual;
    }

    public static void setaAtual(Administrador aAtual) {
        Banco.aAtual = aAtual;
    }

    public static boolean isGerente() {
        return gerente;
    }

    public static void setGerente(boolean gerente) {
        Banco.gerente = gerente;
    }
    ///////////////////////////////////////////////////////////////////
    
    
    /////////////////////////////// Métodos
    /// VERIFICAR SE HA GERENTE CADASTRO //////////////////////
    public static boolean veririficarGerente(){
        if(listaAdm.isEmpty()==false){
        for(Administrador a : listaAdm){
            if (a.getFuncao().equals("GERENTE")){
                setGerente(true);
                return true;
            }
        }
            setGerente(false);
            return false;
        }else{
            setGerente(false);
            return false;
        }
    }
    
    
    public static boolean loginCliente(String login, String senha) {

        for (Cliente listaCliente : listaClientes) {
            if (login.equals(listaCliente.getLogin()) && senha.equals(listaCliente.getSenha())) {
                cAtual = listaCliente;
                return true;
            }
        }
        return false;

    }

    public static boolean loginAdmin(String login, String senha) {

        for (Administrador adm : listaAdm) {
            if (login.equals(adm.getLogin()) && senha.equals(adm.getSenha())) {
                aAtual = adm;
                return true;
            }
        }
        return false;
    }
    
    public static void addAdmin(Administrador administrador){
        listaAdm.add(administrador);
    }
    public static void addCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    public static boolean removerAdmin(Administrador administrador){
        for(Administrador a : listaAdm){
            if(a.getCpf().equals(administrador.getCpf())){
                String cpfGerente = administrador.getCpf();
                listaAdm.remove(a);
                for(Cliente c : listaClientes){
                    if (c.getGerente().getCpf() == cpfGerente){
                        c.setGerente(sortearGerente());
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean removerCliente(Cliente cliente){
        if(getListaClientes().isEmpty()==true){
            return false;
        }else{
            for(Cliente c : listaClientes){
                if (c.getCpf().equals(cliente.getCpf())){
                    listaClientes.remove(cliente);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public static Cliente buscaCpf(String cpf){
        for (Cliente c : listaClientes){
            if (c.getCpf().equals(cpf))
                return c;
        }
        return null;
    }
    public static Cliente buscaId(int id){
        for (Cliente c : listaClientes){
            if (c.getId() == id)
                return c;
        }
        return null;
    }
    public static Cliente buscaNome(String nome){
        for (Cliente c : listaClientes){
            if (c.getNome().equals(nome))
                return c;
        }
        return null;
    }
    public static Cliente buscaLogin(String login) {
        for(Cliente c : listaClientes) {
            if(c.getLogin().equals(login))
                return c;
        }
        return null;
    }
    public static Administrador buscaLoginAdm(String login) {
        for(Administrador a : listaAdm) {
            if(a.getLogin().equals(login))
                return a;
        }
        return null;
    }
    public static Administrador buscaCpfAdm(String cpf){
        for (Administrador a : listaAdm){
            if (a.getCpf().equals(cpf))
                return a;
        }
        return null;
    }
    public static Administrador buscaIdAdm(int id){
        for (Administrador a : listaAdm){
            if (a.getId() == id)
                return a;
        }
        return null;
    }
    public static Administrador buscaNomeAdm(String nome){
        for (Administrador a : listaAdm){
            if (a.getNome().equals(nome))
                return a;
        }
        return null;
    }
    
    public static Gerente sortearGerente() {
        if(veririficarGerente()){
        Gerente g = null;
        int tamLista = listaAdm.size();
        do{
            int sorteio = (int) (Math.random() * tamLista);
            if(listaAdm.get(sorteio).getFuncao().equals("GERENTE")) {
                return (Gerente) listaAdm.get(sorteio);
            }
        } while(g==null);
        
        }
        return null;
    }
    
    
}
