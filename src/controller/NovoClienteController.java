/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Banco;
import classes.Cliente;
import static classes.DiretorPrincipal.stage;
import classes.Gerente;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class NovoClienteController implements Initializable {

    @FXML    private PasswordField txtNovoClienteSenha;
    @FXML    private TextField txtNovoClienteId;
    @FXML    private Button btnCadastrar;
    @FXML    private Button btnFechar;
    @FXML    private TextField txtNovoClienteCpf;
    @FXML    private TextField txtNovoClienteTelefone;
    @FXML    private TextField txtNovoClienteLogin;
    @FXML    private Button btnLimpar;
    @FXML    private Button btnNovo;
    @FXML    private TextField txtNovoClienteNome;
    @FXML    private TextField txtNovoClienteGerente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnFechar.setDisable(false);
        btnLimpar.setDisable(true);
        btnNovo.setDisable(false);
        btnCadastrar.setDisable(true);
        txtNovoClienteNome.setDisable(true);
        txtNovoClienteSenha.setDisable(true);
        txtNovoClienteLogin.setDisable(true);
        txtNovoClienteTelefone.setDisable(true);
        txtNovoClienteCpf.setDisable(true);
        
        txtNovoClienteNome.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoClienteSenha.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoClienteLogin.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoClienteTelefone.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        txtNovoClienteCpf.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        btnCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
    }    
    
    @FXML
    public void limpar() {
        txtNovoClienteCpf.setText("");
        txtNovoClienteId.setText("");
        txtNovoClienteLogin.setText("");
        txtNovoClienteSenha.setText("");
        txtNovoClienteTelefone.setText("");
        txtNovoClienteNome.setText("");
        txtNovoClienteGerente.setText("");
        btnLimpar.setDisable(true);
    }
    @FXML
    private void adicionarCliente(){
        if( txtNovoClienteCpf.getText().equals("") || txtNovoClienteNome.getText().equals("") ||
        txtNovoClienteLogin.getText().equals("") ||  txtNovoClienteSenha.getText().equals("") ||
        txtNovoClienteTelefone.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Preencha todos campos!");
        } else {
            
            String nome = txtNovoClienteNome.getText().toUpperCase();
            String cpf = txtNovoClienteCpf.getText();
            String telefone = txtNovoClienteTelefone.getText();
            String login = txtNovoClienteLogin.getText();
            String senha = txtNovoClienteSenha.getText();
            
            if( Banco.buscaLogin(login)==null && Banco.buscaCpf(cpf)==null && Banco.buscaCpfAdm(cpf)==null && Banco.buscaLoginAdm(login)==null) {
                Cliente c;
                if(Banco.getaAtual().getFuncao().equals("DIRETOR"))
                c = new Cliente(nome, telefone, cpf, login, senha, Banco.sortearGerente());
                else
                c = new Cliente(nome, telefone, cpf, login, senha, (Gerente) Banco.getaAtual());    
                Banco.addCliente(c);
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                btnCadastrar.setDisable(true);
                txtNovoClienteId.setText(Integer.toString(c.getId()));
                
                if(Banco.veririficarGerente())
                txtNovoClienteGerente.setText(c.getGerente().getNome());
                else
                txtNovoClienteGerente.setText("Sem Gerente");
                
                btnNovo.setDisable(false);
                txtNovoClienteId.setDisable(false);
                txtNovoClienteGerente.setDisable(false);
                ///// NAO EDITAVEIS PARA VISUALIZAÇÃO  /////
                txtNovoClienteCpf.setEditable(false);
                txtNovoClienteId.setEditable(false);
                txtNovoClienteLogin.setEditable(false);
                txtNovoClienteSenha.setEditable(false);
                txtNovoClienteTelefone.setEditable(false);
                txtNovoClienteNome.setEditable(false);
                txtNovoClienteGerente.setEditable(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "CPF ou Login já cadastrado!");
            }
        }
    }
    
    @FXML
    private void novo(){
        
        txtNovoClienteId.setDisable(true);
        txtNovoClienteGerente.setDisable(true);
        txtNovoClienteNome.setDisable(false);
        txtNovoClienteSenha.setDisable(false);
        txtNovoClienteLogin.setDisable(false);
        txtNovoClienteTelefone.setDisable(false);
        txtNovoClienteCpf.setDisable(false);
        btnNovo.setDisable(true);
        btnCadastrar.setDisable(false);
        limpar();
        //btnLimpar.setDisable(true);
        txtNovoClienteCpf.setEditable(true);
        txtNovoClienteLogin.setEditable(true);
        txtNovoClienteSenha.setEditable(true);
        txtNovoClienteTelefone.setEditable(true);
        txtNovoClienteNome.setEditable(true);
    }
    @FXML
    private void fechar(){
        limpar();
        stage.close();
        }
    
}
