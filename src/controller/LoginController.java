package controller;

import classes.Banco;
import classes.Cliente;
import classes.ClientePrincipal;
import classes.Diretor;
import classes.DiretorPrincipal;
import classes.Gerente;
import classes.GerentePrincipal;
import classes.Login;
import classes.Notificacao;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    
    private static Stage s;
    public static Stage getStage() {
        return s;
    }
    public static void setStage(Stage stage){
        LoginController.s = stage;
    }
    
    /*
    @FXML private Button btnCliente;
    @FXML private Button btnDiretor;
    @FXML private Button btnGerente;
    */
    @FXML private Button btnConectar;
    @FXML private Button btnClientesTeste;
    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;
    
    @FXML
    private void login()throws Exception{
        boolean admin, cliente=false;
        
        admin = Banco.loginAdmin(txtLogin.getText(), txtSenha.getText());
        if (admin){
            if(Banco.getaAtual().getFuncao().equals("DIRETOR")) {
                Login.getStage().close();
                DiretorPrincipal dp = new DiretorPrincipal();
                dp.start(new Stage());
            } else {
                Login.getStage().close();
                GerentePrincipal gp = new GerentePrincipal();
                gp.start(new Stage());
            }
             
        }
        else{
            cliente = Banco.loginCliente(txtLogin.getText(), txtSenha.getText());
                if (cliente){
                    Login.getStage().close();
                    ClientePrincipal cp = new ClientePrincipal();
                    cp.start(new Stage());                  
                }
        }
        if(!admin && !cliente)
            JOptionPane.showMessageDialog(null, "Usuário e/ou Senha inválidos!");
    }
    
    
    @FXML
    private void encerrar(){
        JOptionPane.showMessageDialog(null, "Volte sempre! :)");
        Login.getStage().close();
    }
    
    @FXML
    private void inserirClientesTeste() {
        Banco.setBotaoClientes(true);
        Gerente g1 = new Gerente("RICARDO S.", "9.9649.5764", "019.555.663-71", "ricardo", "123");
        Banco.addAdmin(g1);
        Gerente g2 = new Gerente("VINNY", "9.9649.5764", "019.444.663-00", "vinny", "123");
        Banco.addAdmin(g2);
        for(int x = 0 ; x <= 10 ; x++){
            Gerente g = new Gerente("Gerente"+x, "3422."+x+x,"123.963"+x,"g"+x, "123");
            Banco.addAdmin(g);
        }
        for(int x = 0 ; x <= 100 ; x++){
            Cliente c = new Cliente("Cliente"+x, "3411."+x+x,"123.456"+x,"c"+x, "123",Banco.sortearGerente());
            Banco.addCliente(c);
        }
        
        
        Diretor d = new Diretor("JOAO GUERI", "2111.5555", "123.666.789-99", "joao", "123");
        Banco.addAdmin(d);
        
        Cliente c = new Cliente("NIDSON", "9.9649.5764", "019.333.663-05", "nid", "123", g1);
        Banco.addCliente(c);
        Cliente d2 = new Cliente("CLARA", "21221.3332", "123.222.739-92", "clf", "123",g1);
        Banco.addCliente(d2);
        Cliente d3 = new Cliente("ISRAELY", "21221.3332", "224.111.739-92", "is", "123",g2);
        Banco.addCliente(d3);
        d3.criarContaCorrente("123");
        d2.criarContaPoupanca("123");
        d3.criarContaPoupanca("123");
        
        JOptionPane.showMessageDialog(null, "Clientes inseridos!");
        btnClientesTeste.setVisible(false);
        
        txtLogin.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    login();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });txtSenha.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    login();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
    }
    
    
    
    
    
   ///////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    /*
    @FXML private void trocaGerente() throws IOException{
        Login.getStage().close();
        GerentePrincipal gp = new GerentePrincipal();
        gp.start(new Stage());
    }
    @FXML private void trocaDiretor() throws IOException{
        Login.getStage().close();
        DiretorPrincipal dp = new DiretorPrincipal();
        dp.start(new Stage());
        
    }
    @FXML private void trocaCliente() throws IOException{

        Parent parent = FXMLLoader.load(getClass().getResource("/views/NotificacaoView.fxml")); 
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Notificação");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        setStage(stage);
        stage.showAndWait();
    }
    
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Banco.isRoot()==false){
        Diretor d = new Diretor("root", "xxxx.xxxx", "000.000.000.00", "root", "1234");
        Banco.addAdmin(d);
        Banco.setRoot(true);
        }
        if (Banco.isBotaoClientes()==true){
        btnClientesTeste.setVisible(false);
        Banco.setBotaoClientes(true);
        }
        
        txtLogin.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    login();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });txtSenha.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    login();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
    }    
    
    
}
