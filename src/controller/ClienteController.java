package controller;

import classes.Banco;
import classes.Cliente;
import classes.ClientePrincipal;
import classes.Conta;
import classes.ContaConjunta;
import classes.ContaCorrente;
import classes.ContaPoupanca;
import classes.Extrato;
import classes.Login;
import classes.Notificacao;
import classes.Pessoa;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import exceptions.ErroException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ClienteController implements Initializable {
    private Cliente cliente;
    @FXML private Text lblCliente;
    @FXML private Text novaMensagem;
    
    //// MEUS DADOS /////////////////////////////////////
    @FXML private Button btnMeusDados;
    @FXML private Pane paneMeusDados;
    @FXML private Button btnMeusDadosConfirmar;
    @FXML private Button btnMeusDadosCancelar;
    @FXML private Button btnMeusDadosEditar;
    @FXML private Button btnMeusDadoseExcluirMinhaConta;
    @FXML private Button btnMeusDadosFechar;
    @FXML private TextField txtMeusDadosLogin;
    @FXML private TextField txtMeusDadosTelefone;
    @FXML private TextField txtMeusDadosGerente;
    @FXML private TextField txtMeusDadosNome;
    @FXML private TextField txtMeusDadosId;
    @FXML private TextField txtMeusDadosCpf;
    @FXML private PasswordField txtMeusDadosSenha;
    /// CONTAS ////////////////////////////////
    @FXML private Pane paneContas;
    @FXML private Pane paneTabelaContas;
    @FXML private Pane paneContasDeposito;
    @FXML private Pane paneContasAbrirConta;
    @FXML private Pane paneContasFecharConta;
    @FXML private RadioButton rbContasAbrirContasConjunta;
    @FXML private RadioButton rbContasAbrirContasPoupanca;
    @FXML private RadioButton rbContasAbrirContasCorrente;
    @FXML private ToggleGroup contas;
    @FXML private Button btnContasVoltar;
    @FXML private Button btnContasAbrirContaAbrirConta;
    @FXML private Button btnContasFecharContaFecharConta;
    @FXML private Button btnPedirAumento;
    @FXML private TextField txtContasDepositoValor;
    @FXML private TextField txtContasAbrirContaSegundoTitular;
    @FXML private TextField txtContasAbrirContaSenha;
    @FXML private TextField txtConta;
    @FXML private TextField txtAgencia;
    @FXML private TextField txtTipo;
    @FXML private TextField txtPrimeiroTitular;
    @FXML private TextField txtSegundoTitular;
    @FXML private TextField txtSaldo;
    @FXML private TextField txtSenha;
    @FXML private TextField txtLimite;
    @FXML private PasswordField txtContasFecharContaSenha;
    @FXML private TableView<Conta> tabelaContas;
    @FXML private TableColumn<Conta, String> colunaContasTipo;
    @FXML private TableColumn<Conta, String> colunaContasConta;
    @FXML private TableColumn<Conta, String> colunaContasAgencia;
    
    
    
    
    /// PAGAMENTO ////////////////////////////////
    @FXML private Button btnPagamentos;
    @FXML private Button btnPagar;
    @FXML private Pane panePagamentos;
    @FXML private TextField txtPagamentosValor;
    /// TRANSFERENCIAS ////////////////////////////////
    @FXML private Button btnTransferencias;
    @FXML private Button btnTransferenciasTransferir;
    @FXML private Pane paneTransferencias;
    @FXML private TextField txtTransferenciasConta;
    @FXML private TextField txtTransferenciasAgencia;
    @FXML private TextField txtTransferenciasNome;
    @FXML private TextField txtTransferenciasCpf;
    @FXML private TextField txtTransferenciasValor;
    
    /// MENSAGENS ////////////////////////////////
    @FXML private Button btnMensagens;
    @FXML private Pane paneMensagensVisualizar;
    @FXML private Pane paneMensagens;
    @FXML private Button btnMensagensAbrir;
    @FXML private Button btnMensagensExcluir;
    @FXML private Button btnMensagensAprovar;
    @FXML private Button btnMensagensNegar;
    @FXML private Button btnVerCliente;
    @FXML private TextField txtMensagensMensagem;
    @FXML private TextField txtMensagensRemetente;
    @FXML private TextField txtMensagensTitulo;
    @FXML private TableView<Notificacao> tabelaMensagens;
    @FXML private TableColumn<Notificacao, Pessoa> ColunaMensagensRemetente;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensLido;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensRespondido;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensTitulo;
    private ObservableList observarMensagens;
    private ObservableList observarContas;
    
    ////////////METODOS ///////////////////// METODOS//////////////////////
    @FXML
    private void excluirConta() throws Exception{
        Banco.removerCliente(cliente);
        JOptionPane.showMessageDialog(null, "Você deletou sua conta!");
        ClientePrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    @FXML
    public void logout() throws Exception{
        ClientePrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    @FXML
    private void editarMeusDados(){
        JOptionPane.showMessageDialog(null, "Use os campos de texto para editar!");
        txtMeusDadosNome.setEditable(true);
        txtMeusDadosCpf.setEditable(true);
        txtMeusDadosTelefone.setEditable(true);
        txtMeusDadosLogin.setEditable(false);
        txtMeusDadosSenha.setEditable(true);
        btnMeusDadosEditar.setDisable(true);
        btnMeusDadosCancelar.setDisable(false);
        btnMeusDadosConfirmar.setDisable(false);
    }
    @FXML
    private void confirmarEditarMeusDados(){
        cliente.setNome(txtMeusDadosNome.getText().toUpperCase());
        cliente.setCpf(txtMeusDadosCpf.getText());
        cliente.setTelefone(txtMeusDadosTelefone.getText());
        cliente.setSenha(txtMeusDadosSenha.getText());
        JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso!");
        abrirPainelMeusDados();
    }
    @FXML
    private void cancelarEditarMeusDados(){
        limparTxtMeusDados();
        abrirPainelMeusDados();
    }
    @FXML
    private void limparTxtMeusDados(){
        txtMeusDadosNome.setEditable(false);
        txtMeusDadosCpf.setEditable(false);
        txtMeusDadosTelefone.setEditable(false);
        txtMeusDadosLogin.setEditable(false);
        txtMeusDadosSenha.setEditable(false);
        btnMeusDadosEditar.setDisable(false);
        btnMeusDadosCancelar.setDisable(true);
        btnMeusDadosConfirmar.setDisable(true);
    }
    @FXML
    private void abrirPainelMeusDados() {
        limpar();
        paneMeusDados.setVisible(true);
        txtMeusDadosNome.setText(cliente.getNome());
        txtMeusDadosCpf.setText(cliente.getCpf());
        txtMeusDadosId.setText(Integer.toString(cliente.getId()));
        txtMeusDadosLogin.setText(cliente.getLogin());
        txtMeusDadosTelefone.setText(cliente.getTelefone());
        txtMeusDadosSenha.setText(cliente.getSenha());
        txtMeusDadosGerente.setText(cliente.getGerente().getNome());
        btnMeusDadosEditar.setDisable(false);
        btnMeusDadosConfirmar.setDisable(true);
        btnMeusDadosCancelar.setDisable(true);
    }
    ///////////////////PAINEL PRINCIPAL INICIO //////////////////////////////////////
    @FXML
    private void abrirOpcoesContas(){
        limpar();
        preencherTabelaContas();
        paneTabelaContas.setVisible(true);
        paneContas.setVisible(true);
    }
    @FXML
    private void abrirOpcoesContasDeposito(){
        limpar();
        preencherTabelaContas();
        paneContas.setVisible(true);
        paneTabelaContas.setVisible(true);
        paneContasDeposito.setVisible(true);
    }
    @FXML
    private void confirmarDeposito(){
    if(txtConta.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Selecione a conta!");
    }else{
    try{
        if(Double.parseDouble(txtContasDepositoValor.getText())<0 ){
        JOptionPane.showMessageDialog(null, "Inserir valor válido!");
        }else{
            if(txtTipo.getText().equals("Poupança")){
        
            cliente.getContaPoupanca().depositar(Double.parseDouble(txtContasDepositoValor.getText()));
            JOptionPane.showMessageDialog(null, "Deposito efetuado com sucesso!");
            Extrato e = new Extrato("Depósito POUPANÇA", Double.parseDouble(txtContasDepositoValor.getText()), "O próprio", 0,0,cliente.getContaPoupanca().getAgencia(), cliente.getContaPoupanca().getConta(), cliente.getContaPoupanca().getSaldo());
            cliente.adicionarExtrato(e);
            txtContasDepositoValor.setText("");
        
        }
        else if(txtTipo.getText().equals("Corrente")){
                cliente.getContaCorrente().depositar(Double.parseDouble(txtContasDepositoValor.getText()));
                JOptionPane.showMessageDialog(null, "Deposito efetuado com sucesso!");
                Extrato e = new Extrato("Depósito CORRENTE", Double.parseDouble(txtContasDepositoValor.getText()), "O próprio", 0,0,cliente.getContaCorrente().getAgencia(), cliente.getContaCorrente().getConta(), cliente.getContaCorrente().getSaldo());
                cliente.adicionarExtrato(e);
                txtContasDepositoValor.setText("");
                }
        
        else if(txtTipo.getText().equals("Conjunta")){
                cliente.getContaConjunta().depositar(Double.parseDouble(txtContasDepositoValor.getText()));
                JOptionPane.showMessageDialog(null, "Deposito efetuado com sucesso!");
                Extrato e = new Extrato("Depósito CONJUNTA", Double.parseDouble(txtContasDepositoValor.getText()), cliente.getNome(), 0,0,cliente.getContaConjunta().getAgencia(), cliente.getContaConjunta().getConta(), cliente.getContaConjunta().getSaldo());
                cliente.adicionarExtrato(e);
                Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                cp.adicionarExtrato(e);
                txtContasDepositoValor.setText("");        
        }
            
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Favor inserir numeros!");
    }
    }
    limparTxtContas();
    }
    @FXML
    private void abrirOpcoesContasAbrirConta(){
        limpar();
        paneContas.setVisible(true);
        paneTabelaContas.setVisible(true);
        paneContasAbrirConta.setVisible(true);
        rbContasAbrirContasPoupanca.setSelected(false);
        rbContasAbrirContasCorrente.setSelected(false);
        rbContasAbrirContasConjunta.setSelected(false);
        rbContasAbrirContasConjunta.setDisable(false);
        rbContasAbrirContasConjunta.setDisable(false);
        rbContasAbrirContasConjunta.setDisable(false);
        txtContasAbrirContaSenha.setText("");
        txtContasAbrirContaSegundoTitular.setText("");
        if(cliente.isContaConjuntaOk())
            rbContasAbrirContasConjunta.setDisable(true);
        if(cliente.isContaCorrenteOk())
            rbContasAbrirContasCorrente.setDisable(true);
        if(cliente.isContaPoupancaOk())
            rbContasAbrirContasPoupanca.setDisable(true);
    }
    @FXML
    private void confirmarAbrirConta(){
        if(txtContasAbrirContaSenha.getText().equals("") || rbContasAbrirContasPoupanca.isSelected() == false && rbContasAbrirContasCorrente.isSelected()== false &&rbContasAbrirContasConjunta.isSelected()== false){
            JOptionPane.showMessageDialog(null, "Preencher os campos adequadamente!");
        }else{
            if(rbContasAbrirContasConjunta.isSelected()){
                Cliente segundoCliente = Banco.buscaNome(txtContasAbrirContaSegundoTitular.getText());
                if(segundoCliente==null){
                    JOptionPane.showMessageDialog(null, "Segundo titular não encontrado!");
                }
                else if (segundoCliente.isContaConjuntaOk()== false && cliente.getGerente().getId() == segundoCliente.getGerente().getId()){
                    Cliente primeiroCliente = cliente;
                    cliente.criarContaConjunta(txtContasAbrirContaSenha.getText(), segundoCliente, primeiroCliente);
                    segundoCliente.setContaConjunta(cliente.getContaConjunta());
                    segundoCliente.setContaConjuntaOk(true);
                    paneContasAbrirConta.setVisible(false);
                    tabelaContas.refresh();
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    preencherTabelaContas();
                    Notificacao not = new Notificacao("Conta conjunta criada!", "Voce agora tem uma conta conjunta com "+cliente.getNome(), segundoCliente, cliente, null);
                    segundoCliente.adicionarNotificacao(not);
                }else if(cliente.getGerente().getId() != segundoCliente.getGerente().getId())
                    JOptionPane.showMessageDialog(null, "Voce e esse usuario não possuem o mesmo gerente!");
            }
            else if(rbContasAbrirContasCorrente.isSelected()){
                cliente.criarContaCorrente(txtContasAbrirContaSenha.getText());
                paneContasAbrirConta.setVisible(false);
                tabelaContas.refresh();
                JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                preencherTabelaContas();
            }
            else if(rbContasAbrirContasPoupanca.isSelected()){
                cliente.criarContaPoupanca(txtContasAbrirContaSenha.getText());
                paneContasAbrirConta.setVisible(false);
                tabelaContas.refresh();
                JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                preencherTabelaContas();
            }
        }
        
    }
    @FXML
    private void txtSegundoTitularConjunta(){
        if(rbContasAbrirContasConjunta.isSelected())
            txtContasAbrirContaSegundoTitular.setEditable(true);
        if(rbContasAbrirContasCorrente.isSelected()){
            txtContasAbrirContaSegundoTitular.setEditable(false);
            txtContasAbrirContaSegundoTitular.setText("");
        }
        if(rbContasAbrirContasPoupanca.isSelected()){
            txtContasAbrirContaSegundoTitular.setEditable(false);
            txtContasAbrirContaSegundoTitular.setText("");
        }
    }
    
    
    @FXML
    private void abrirOpcoesContasFecharConta(){
        limpar();
        paneContas.setVisible(true);
        paneTabelaContas.setVisible(true);
        paneContasFecharConta.setVisible(true);
    }
    @FXML
    private void confirmarFecharConta(){
        if(txtTipo.getText().equals("Poupança")){
            ContaPoupanca cp = cliente.getContaPoupanca();
            if(cp.getSenha().equals(txtContasFecharContaSenha.getText())){
                if(cp.getSaldo()!=0){
                    JOptionPane.showMessageDialog(null, "Não é possivel fechar conta, saldo diferente de zero!");
                }else{
                cliente.deletarContaPoupanca();
                JOptionPane.showMessageDialog(null, "Conta Poupança deletada com sucesso!");
                tabelaContas.refresh();
                preencherTabelaContas();
                }
            }
        }
        else if(txtTipo.getText().equals("Corrente")){
                ContaCorrente cc = cliente.getContaCorrente();
                if(cc.getSenha().equals(txtContasFecharContaSenha.getText())){
                    if(cc.getSaldo()!=0){
                    JOptionPane.showMessageDialog(null, "Não é possivel fechar conta, saldo diferente de zero!");
                    }else{cliente.deletarContaCorrente();
                JOptionPane.showMessageDialog(null, "Conta Corrente deletada com sucesso!");
                tabelaContas.refresh();
                preencherTabelaContas();
                }
        }
        }
        else if(txtTipo.getText().equals("Conjunta")){
                ContaConjunta cj = cliente.getContaConjunta();
                if(cj.getSenha().equals(txtContasFecharContaSenha.getText())){
                    if(cj.getSaldo()!=0){
                    JOptionPane.showMessageDialog(null, "Não é possivel fechar conta, saldo diferente de zero!");
                    }else{cliente.deletarContaConjunta();
                    JOptionPane.showMessageDialog(null, "Conta Conjunta deletada com sucesso!");
                    tabelaContas.refresh();
                    preencherTabelaContas();
                    }
                }
        }else
            JOptionPane.showMessageDialog(null, "Dados incorretos!");
        limparTxtContas();
    }
    
    @FXML
    private void abrirOpcoesPagamento(){
        limpar();
        paneTabelaContas.setVisible(true);
        preencherTabelaContas();
        panePagamentos.setVisible(true);
    }
    
    @FXML
    private void confirmarPagamento() throws ErroException{
        try{
        if(txtConta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione alguma conta na tabela!");
        }else if(txtPagamentosValor.getText().equals("") || Double.parseDouble(txtPagamentosValor.getText())<=0){
            JOptionPane.showMessageDialog(null, "Valor incorreto!");
        }else{
        if(txtTipo.getText().equals("Poupança")){
            JOptionPane.showMessageDialog(null, "Não pode pagar da Poupança!");
            txtContasDepositoValor.setText("");
        }
        else if(txtTipo.getText().equals("Corrente")){
                try{
                cliente.getContaCorrente().pagar(Double.parseDouble(txtPagamentosValor.getText()));
                Extrato e = new Extrato("PAGAMENTO", Double.parseDouble(txtPagamentosValor.getText()), "Empresa X", 0, 0, cliente.getContaCorrente().getAgencia(), cliente.getContaCorrente().getConta(), cliente.getContaCorrente().getSaldo());
                cliente.adicionarExtrato(e);
                }catch(ErroException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                
                txtContasDepositoValor.setText("");
        }
        
        else if(txtTipo.getText().equals("Conjunta")){
                try{
                if(Double.parseDouble(txtPagamentosValor.getText())>=1000){
                    Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                    
                    Notificacao n = new Notificacao("Confirmacao para pagamento","Seu socio está tentando efetuar um pagamento de: "+txtPagamentosValor.getText()+" R$ \nEssa quantia precisa ser aprovada por todos os titulares da conta!", cp, cliente,  null);
                    n.setValor(Double.parseDouble(txtPagamentosValor.getText()));
                    cp.adicionarNotificacao(n);
                    JOptionPane.showMessageDialog(null, "Foi enviada uma mensagem ao outro titular da conta, essa quantia precisa ser aprovada!");
                }else{
                cliente.getContaConjunta().pagar(Double.parseDouble(txtPagamentosValor.getText()));
                Extrato e = new Extrato("PAGAMENTO", Double.parseDouble(txtPagamentosValor.getText()), "Empresa X", 0, 0, cliente.getContaConjunta().getAgencia(), cliente.getContaConjunta().getConta(), cliente.getContaConjunta().getSaldo());
                cliente.adicionarExtrato(e);
                Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                    cp.adicionarExtrato(e);
                }
                }catch(ErroException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                
                txtContasDepositoValor.setText("");      
        }
        }
        }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
        }
        tabelaContas.refresh();
        preencherTabelaContas();
        
        
    }
    
    @FXML
    private void abrirOpcoesTransferencias(){
        limpar();
        preencherTabelaContas();
        paneTabelaContas.setVisible(true);
        paneTransferencias.setVisible(true);
        
    }
    @FXML
    private void confirmarTransferencia() throws ErroException, Exception{
        try{
        if (txtTransferenciasConta.getText().equals("") || txtTransferenciasAgencia.getText().equals("") ||
                txtTransferenciasCpf.getText().equals("") || txtTransferenciasNome.getText().equals("") || txtTransferenciasValor.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        }else if(txtConta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione sua conta!");
        }else{
            int contaB = Integer.parseInt(txtTransferenciasConta.getText());
            int agenciaB = Integer.parseInt(txtTransferenciasAgencia.getText());
            String nomeB = txtTransferenciasNome.getText();
            String cpf = txtTransferenciasCpf.getText();
            double valor = Double.parseDouble(txtTransferenciasValor.getText());
            Conta conta = null;
            
            Cliente clienteB = Banco.buscaCpf(cpf);
            Cliente comp = Banco.buscaNome(nomeB);
            
            if(clienteB == comp){
                if(clienteB.getContaPoupanca()!=null)
                if(clienteB.getContaPoupanca().getConta() == contaB && clienteB.getContaPoupanca().getAgencia()==agenciaB){
                   conta = clienteB.getContaPoupanca();
                }
                if(clienteB.getContaCorrente()!=null)
                if(clienteB.getContaCorrente().getConta() == contaB && clienteB.getContaCorrente().getAgencia()==agenciaB){
                   conta = clienteB.getContaCorrente();
                }
                if(clienteB.getContaConjunta()!=null)
                if(clienteB.getContaConjunta().getConta() == contaB && clienteB.getContaConjunta().getAgencia()==agenciaB){
                   conta = clienteB.getContaConjunta();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
            if(conta != null){
            if(txtTipo.getText().equals("Poupança")){
                try{
                cliente.getContaPoupanca().transferencia(conta, cliente.getContaPoupanca(), clienteB, cliente, valor);
                Extrato extrato = new Extrato("TRANSFERENCIA", valor, clienteB.getNome(), agenciaB, contaB, cliente.getContaPoupanca().getAgencia(),cliente.getContaPoupanca().getConta(),cliente.getContaPoupanca().getSaldo());
                cliente.adicionarExtrato(extrato);
                
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Erro ocorrido ao transferir: "+e);
                }
            }
            else if(txtTipo.getText().equals("Corrente")){
                try{
                cliente.getContaCorrente().transferencia(conta, cliente.getContaCorrente(), clienteB, cliente, valor);
                Extrato extrato = new Extrato("TRANSFERENCIA", valor, clienteB.getNome(), agenciaB, contaB, cliente.getContaCorrente().getAgencia(),cliente.getContaCorrente().getConta(),cliente.getContaCorrente().getSaldo());
                Extrato extrato2 = new Extrato("TRANSFERENCIA", valor, clienteB.getNome(), agenciaB, contaB, cliente.getContaCorrente().getAgencia(),cliente.getContaCorrente().getConta(),conta.getSaldo());
                cliente.adicionarExtrato(extrato);
                clienteB.adicionarExtrato(extrato2);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Erro ocorrido ao transferir: "+e);
                }
            }
            
            else if(txtTipo.getText().equals("Conjunta")){
                try{
                cliente.getContaConjunta().transferencia(conta, cliente.getContaConjunta(), clienteB, cliente, valor);
                Extrato extrato = new Extrato("TRANSFERENCIA", valor, clienteB.getNome(), agenciaB, contaB, cliente.getContaConjunta().getAgencia(),cliente.getContaConjunta().getConta(),cliente.getContaConjunta().getSaldo());
                Extrato extrato2 = new Extrato("TRANSFERENCIA", valor, clienteB.getNome(), agenciaB, contaB, cliente.getContaConjunta().getAgencia(),cliente.getContaConjunta().getConta(),conta.getSaldo());
                cliente.adicionarExtrato(extrato);
                clienteB.adicionarExtrato(extrato2);
                Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                cp.adicionarExtrato(extrato);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Erro ocorrido ao transferir: "+e);
                }       
            }
           }else{
                JOptionPane.showMessageDialog(null, "Conferir conta ou agencia do beneficiado!");
            }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao digitar String: \n"+e);
        }
    
    }
    
    @FXML
    private void abrirPainelMensagens() {
        limpar();
        paneMensagens.setVisible(true);
        ColunaMensagensTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        ColunaMensagensRemetente.setCellValueFactory(new PropertyValueFactory<>("remetente"));
        ColunaMensagensLido.setCellValueFactory(new PropertyValueFactory<>("visualizado"));
        ColunaMensagensRespondido.setCellValueFactory(new PropertyValueFactory<>("respondido"));
        observarMensagens = FXCollections.observableArrayList(cliente.getListaNotificacoes());
        tabelaMensagens.setItems(observarMensagens);
    }
    @FXML
    private void selecionarTabelaMensagens(){
        btnMensagensAbrir.setDisable(false);
        btnMensagensExcluir.setDisable(false);
    }
    @FXML
    private void abrirMensagem(){
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        if(n!=null){
        n.setVisualizado(true);
        if(cliente.verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
        else
            novaMensagem.setVisible(false);
        txtMensagensTitulo.setText(n.getTitulo());
        txtMensagensRemetente.setText(n.getRemetente().getNome());
        txtMensagensMensagem.setText(n.getMensagem());
        tabelaMensagens.refresh();
        paneMensagensVisualizar.setVisible(true);
        if(n.getRemetente()==cliente.getGerente() || n.getTitulo().equals("Conta conjunta criada!") || n.getTitulo().equals("Resposta a Solicitação de Pagamento")){
            btnMensagensAprovar.setDisable(true);
            btnMensagensNegar.setDisable(true);
        }else{
            if(n.isRespondido()==false) {
              btnMensagensAprovar.setDisable(false);
              btnMensagensNegar.setDisable(false);
            }else{
              btnMensagensAprovar.setDisable(true);
              btnMensagensNegar.setDisable(true);
            }
        }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhuma mensagem selecionada!");
        }
        
    }
    @FXML
    private void excluirMensagem(){
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        if(n==null){
            JOptionPane.showMessageDialog(null, "Nenhuma mensagem selecionada!");
        }else{
        cliente.excluirNotificacao(n);
        JOptionPane.showMessageDialog(null, "Mensagem excluida com sucesso!");
        if(cliente.verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
        else
            novaMensagem.setVisible(false);
        }
        abrirPainelMensagens();
    }
    
    @FXML
    private void aprovarMensagem() throws ErroException, InvocationTargetException{
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        n.setRespondido(true);
        n.setOk(true);
        try{
        cliente.getContaConjunta().pagar(n.getValor()); ///VERIFICAR
        Extrato e = new Extrato("PAGAMENTO", n.getValor(), "Empresa X", 0, 0, cliente.getContaConjunta().getAgencia(), cliente.getContaConjunta().getConta(), cliente.getContaConjunta().getSaldo());
                cliente.adicionarExtrato(e);
                Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                    
                    cp.adicionarExtrato(e);
        }catch(ErroException e){
            JOptionPane.showMessageDialog(null, e);
        }
        Cliente c;
        if(cliente == n.getDestinatario()){
            c = (Cliente) n.getRemetente();
        }else
            c = (Cliente) n.getDestinatario();
        Notificacao resposta = new Notificacao("Resposta a Solicitação de Pagamento", "Sua solicitação de pagamento de "+n.getValor()+" R$ foi aceita pelo outro titular da conta! Verifique o saldo da sua conta!", c, cliente, null);
        c.adicionarNotificacao(resposta);
        JOptionPane.showMessageDialog(null, "Sua resposta foi enviada ao solicitante!");
        btnMensagensAprovar.setDisable(true);
        btnMensagensNegar.setDisable(true);
        tabelaMensagens.refresh();
    }
    
    @FXML
    private void negarMensagem() {
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        n.setRespondido(true);
        n.setOk(false);
        Cliente c;
        if(cliente == n.getDestinatario()){
            c = (Cliente) n.getRemetente();
        }else
            c = (Cliente) n.getDestinatario();
        
        Notificacao resposta = new Notificacao("Resposta a Solicitação de Pagamento", "Sua solicitação de pagamento de "+n.getValor()+" R$ foi negada pelo outro titular da conta!", c, cliente, null);
        c.adicionarNotificacao(resposta);
        JOptionPane.showMessageDialog(null, "Sua resposta foi enviada ao solicitante!");
        btnMensagensAprovar.setDisable(true);
        btnMensagensNegar.setDisable(true);
        tabelaMensagens.refresh();
    }
    
    @FXML
    private void preencherTabelaContas(){
        colunaContasConta.setCellValueFactory(new PropertyValueFactory<>("conta"));
        colunaContasAgencia.setCellValueFactory(new PropertyValueFactory<>("agencia"));
        colunaContasTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        observarContas = FXCollections.observableArrayList();
        if(cliente.isContaCorrenteOk())
            observarContas.add(cliente.getContaCorrente());
        if(cliente.isContaPoupancaOk())
            observarContas.add(cliente.getContaPoupanca());
        if(cliente.isContaConjuntaOk())
            observarContas.add(cliente.getContaConjunta());
        
        tabelaContas.setItems(observarContas);
    }
    @FXML
    private void selecionarTabelaClientesContas() {
        Conta conta = tabelaContas.getSelectionModel().getSelectedItem();
        txtAgencia.setText(Integer.toString(conta.getAgencia()));
        if (conta.getTipo().equals("Poupança"))
        txtConta.setText("0"+Integer.toString(conta.getConta()));
        else
        txtConta.setText(Integer.toString(conta.getConta()));
        txtSaldo.setText(Double.toString(conta.getSaldo()));
        txtSenha.setText(conta.getSenha());
        txtTipo.setText(conta.getTipo());
        
        if(conta.getTipo().equals("Conjunta")){
            ContaConjunta c = (ContaConjunta)tabelaContas.getSelectionModel().getSelectedItem();
            txtPrimeiroTitular.setText(c.getPrimeiroCliente().getNome());
            txtSegundoTitular.setText(c.getSegundoCliente().getNome());
            
        }else if(conta.getTipo().equals("Corrente")){
            txtSegundoTitular.setText("");
        }else
            txtSegundoTitular.setText("");
        
        if(conta.getTipo().equals("Corrente") || conta.getTipo().equals("Conjunta")){
            ContaCorrente cc = (ContaCorrente)conta;
            txtLimite.setText(Double.toString(cc.getLimiteTotal()));
            if(cliente.getGerente().notificacaoEnviada(cliente) == true)
            btnPedirAumento.setDisable(true);
            else
            btnPedirAumento.setDisable(false);
        }else{
            btnPedirAumento.setDisable(true);
            txtLimite.setText("");
        }
    }
    @FXML
    private void pedirAumento(){
        Conta conta = tabelaContas.getSelectionModel().getSelectedItem();
        
            if(conta.getTipo().equals("Conjunta")){
            if(cliente.getContaConjunta().getSegundoCliente() == cliente){
            JOptionPane.showMessageDialog(null, "Apenas o primeiro titular da conta pode pedir aumento de limite!");
            }else{
            Notificacao not = new Notificacao("Aumento de Limite", "Olá senhor gerente, poderia conceder um aumento de Limite?", cliente.getGerente(), cliente, conta);
            cliente.getGerente().adicionarNotificacao(not);
            JOptionPane.showMessageDialog(null, "Notificacao enviada a seu gerente. Aguarde a resposta!");
            btnPedirAumento.setDisable(true);
            }       
        }else{
            Notificacao not = new Notificacao("Aumento de Limite", "Olá senhor gerente, poderia conceder um aumento de Limite?", cliente.getGerente(), cliente, conta);
            cliente.getGerente().adicionarNotificacao(not);
            JOptionPane.showMessageDialog(null, "Notificacao enviada a seu gerente. Aguarde a resposta!");
            btnPedirAumento.setDisable(true);
        }
        
    }
    
    @FXML
    private void limpar(){
        paneMeusDados.setVisible(false);
        paneContas.setVisible(false);
        paneTabelaContas.setVisible(false);
        paneMensagens.setVisible(false);
        paneMensagensVisualizar.setVisible(false);
        panePagamentos.setVisible(false);
        paneTransferencias.setVisible(false);
        paneContasAbrirConta.setVisible(false);
        paneContasDeposito.setVisible(false);
        paneContasFecharConta.setVisible(false);
    }
    @FXML
    private void limparTxtContas(){
        txtAgencia.setText("");
        txtConta.setText("");
        txtSaldo.setText("");
        txtSegundoTitular.setText("");
        txtPrimeiroTitular.setText("");
        txtSenha.setText("");
        txtTipo.setText("");
        txtLimite.setText("");
        btnPedirAumento.setDisable(true);
        
    }
    @FXML
    private void extrato() throws FileNotFoundException, DocumentException, IOException{
                
        Document pdf = new Document() {}; 
        PdfWriter.getInstance(pdf, new FileOutputStream("Extrato.pdf")); // nome do arquivo
        pdf.open(); // abrindo o arquivo para poder editar
        pdf.add(new Paragraph("                   EXTRATO BANCÁRIO        \n\n"));
        pdf.add(new Paragraph("Número de cadastro: "+cliente.getId()));
        pdf.add(new Paragraph("Cliente: "+cliente.getNome()));
        pdf.add(new Paragraph("CPF: "+cliente.getCpf()));
        pdf.add(new Paragraph("Telefone: "+cliente.getTelefone()));
        pdf.add(new Paragraph("Login: "+cliente.getLogin()));
        pdf.add(new Paragraph("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" ));
        pdf.add(new Paragraph("\nHISTÓRICO DO CLIENTE: \n" ));
        pdf.add(new Paragraph("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\n" ));
        
        for (Extrato e : cliente.getHistorico()){
        pdf.add(new Paragraph("Operação: "+e.getOperacao()));
        pdf.add(new Paragraph("Conta Titular: "+e.getContaTitular()));
        pdf.add(new Paragraph("Agencia Titular: "+e.getAgenciaTitular()));
        pdf.add(new Paragraph("Beneficiado: "+e.getBeneficiado()));
        pdf.add(new Paragraph("Conta: "+e.getContaB()));
        pdf.add(new Paragraph("Agencia: "+e.getAgenciaB()));
        pdf.add(new Paragraph("Valor da operação: "+e.getValor()+" R$"));
        pdf.add(new Paragraph("Saldo: "+e.getSaldoAtual()+" R$"));
        pdf.add(new Paragraph("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" ));  
        }
        pdf.close();
        Desktop.getDesktop().open(new File("Extrato.pdf"));
    }
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        novaMensagem.setVisible(false);
        limpar();
        this.cliente = Banco.getcAtual();
        lblCliente.setText(Banco.getcAtual().getNome());
        if(cliente.verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
    }    
    
}
