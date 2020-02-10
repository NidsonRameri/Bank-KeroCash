package controller;

import classes.Administrador;
import classes.Banco;
import classes.Diretor;
import static classes.DiretorPrincipal.stage;
import classes.Gerente;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class NovoAdminController implements Initializable {

    @FXML    private PasswordField txtNovoAdmSenha;
    @FXML    private TextField txtNovoAdmId;
    @FXML    private Button btnCadastrar;
    @FXML    private Button btnFechar;
    @FXML    private TextField txtNovoAdmCpf;
    @FXML    private TextField txtNovoAdmTelefone;
    @FXML    private TextField txtNovoAdmLogin;
    @FXML    private Button btnLimpar;
    @FXML    private Button btnNovo;
    @FXML    private TextField txtNovoAdmNome;
    @FXML    private ComboBox cbNovoAdmFuncao;
    
    @FXML
    public void limpar() {
        txtNovoAdmCpf.setText("");
        txtNovoAdmId.setText("");
        txtNovoAdmLogin.setText("");
        txtNovoAdmSenha.setText("");
        txtNovoAdmTelefone.setText("");
        txtNovoAdmNome.setText("");
        //cbNovoAdmFuncao.setText("");  
    }
    @FXML
    private void novo(){
        txtNovoAdmNome.setDisable(false);
        txtNovoAdmSenha.setDisable(false);
        txtNovoAdmLogin.setDisable(false);
        txtNovoAdmTelefone.setDisable(false);
        txtNovoAdmCpf.setDisable(false);
        btnNovo.setDisable(true);
        btnCadastrar.setDisable(false);
        limpar();
    }
    @FXML
    private void fechar(){
        limpar();
        stage.close();
    }
    
    
    @FXML
    private void adicionarAdm(){
        if( txtNovoAdmCpf.getText().equals("") || txtNovoAdmNome.getText().equals("") ||
        txtNovoAdmLogin.getText().equals("") ||  txtNovoAdmSenha.getText().equals("") ||
        txtNovoAdmTelefone.getText().equals("") || cbNovoAdmFuncao == null) {
            JOptionPane.showMessageDialog(null, "Preencha todos campos!");
        } else {
            
            String nome = txtNovoAdmNome.getText().toUpperCase();
            String cpf = txtNovoAdmCpf.getText();
            String telefone = txtNovoAdmTelefone.getText();
            String login = txtNovoAdmLogin.getText();
            String senha = txtNovoAdmSenha.getText();
            String funcao = (String) cbNovoAdmFuncao.getValue();
            
            if( Banco.buscaLogin(login)==null && Banco.buscaCpf(cpf)==null && Banco.buscaCpfAdm(cpf)==null && Banco.buscaLoginAdm(login)==null) {
                Administrador a;
                if(funcao.equals("GERENTE")) {
                    a = new Gerente(nome, telefone, cpf, login, senha);
                    Banco.addAdmin(a);
                } else {
                    a = new Diretor(nome, telefone, cpf, login, senha);
                    Banco.addAdmin(a);
                }
                
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                btnCadastrar.setDisable(true);
                txtNovoAdmId.setText(Integer.toString(a.getId()));
                btnNovo.setDisable(false);
                txtNovoAdmId.setDisable(false);
                ///// NAO EDITAVEIS PARA VISUALIZAÇÃO  /////
                cbNovoAdmFuncao.setEditable(false);
                txtNovoAdmCpf.setEditable(false);
                txtNovoAdmId.setEditable(false);
                txtNovoAdmLogin.setEditable(false);
                txtNovoAdmSenha.setEditable(false);
                txtNovoAdmTelefone.setEditable(false);
                txtNovoAdmNome.setEditable(false);
            }else{
                JOptionPane.showMessageDialog(null, "CPF ou Login já cadastrado!");
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnFechar.setDisable(false);
        btnLimpar.setDisable(true);
        btnNovo.setDisable(false);
        btnCadastrar.setDisable(true);
        txtNovoAdmNome.setDisable(true);
        txtNovoAdmSenha.setDisable(true);
        txtNovoAdmLogin.setDisable(true);
        txtNovoAdmTelefone.setDisable(true);
        txtNovoAdmCpf.setDisable(true);
        cbNovoAdmFuncao.getItems().addAll("DIRETOR","GERENTE");
        cbNovoAdmFuncao.getSelectionModel().select(0);
        
        txtNovoAdmNome.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarAdm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoAdmSenha.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarAdm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoAdmLogin.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarAdm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoAdmTelefone.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarAdm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoAdmCpf.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarAdm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
        
        
    }    
    
}
