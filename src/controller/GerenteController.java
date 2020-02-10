/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Administrador;
import classes.Banco;
import classes.Cliente;
import classes.Conta;
import classes.Extrato;
import classes.Gerente;
import classes.GerentePrincipal;
import classes.Login;
import classes.Notificacao;
import classes.Pessoa;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class GerenteController implements Initializable {
 // PANE MEUS DADOS //////////////////////////////////////
    @FXML private Pane paneMeusDados;
    @FXML private PasswordField txtMeusDadosSenha;
    @FXML private Button btnMeusDadosConfirmar;
    @FXML private Button btnMeusDadosCancelar;
    @FXML private Button btnMeusDadosEditar;
    @FXML private Button btnMeusDados;
    @FXML private Button btnMeusDadoseExcluirMinhaConta;
    @FXML private Button btnMeusDadosFechar;
    @FXML private TextField txtMeusDadosLogin;
    @FXML private TextField txtMeusDadosTelefone;
    @FXML private TextField txtMeusDadosFuncao;
    @FXML private TextField txtMeusDadosNome;
    @FXML private TextField txtMeusDadosId;
    @FXML private TextField txtMeusDadosCpf;
    // PANE CLIENTES  //////////////////////////////////////
    @FXML private TextField txtClientesId;
    @FXML private TextField txtClientesGerente;
    @FXML private TextField txtClientesTelefone;
    @FXML private TextField txtClientesNome;
    @FXML private TextField txtClientesAgencia;
    @FXML private TextField txtClientesConta;
    @FXML private TextField txtClientesCpf;
    @FXML private TextField txtClientesLogin;
    @FXML private TextField txtClientesBusca;
    @FXML private TextField txtClientesTipo;
    @FXML private TextField txtClientesSaldo;
    @FXML private PasswordField txtClientesSenha;
    @FXML private Button btnClientesBuscar;
    @FXML private Button btnClientesPdf;
    @FXML private Button btnClientesExcluir;
    @FXML private Button btnClientesExtrato;
    @FXML private Button btnClientesCancelar;
    @FXML private Button btnClientesEditar;
    @FXML private Button btnClientes;
    @FXML private Button btnClientesFechar;
    @FXML private Button btnClientesConfirmar;
    @FXML private ComboBox<String> cbClientesBusca;
    @FXML private TableView<Cliente> tabelaClientesInfor;
    @FXML private TableColumn<Cliente, String> ColunaClienteId;
    @FXML private TableColumn<Cliente, String> ColunaClienteNome;
    @FXML private TableColumn<Cliente, String> ColunaClienteLogin;
    @FXML private TableColumn<Cliente, String> ColunaClienteCpf;
    @FXML private TableView<Conta> tabelaClientesContas;
    @FXML private TableColumn<Conta, String> ColunaClienteTipo;
    @FXML private TableColumn<Conta, String> ColunaClienteConta;
    @FXML private TableColumn<Conta, String> ColunaClienteAgencia;
    @FXML private TableColumn<Conta, String> ColunaClienteSaldo;
    @FXML private Pane paneClientes;
    // PANE MENSAGENS //////////////////////////////////////
    @FXML private Pane paneMensagensVisualizar;
    @FXML private Pane paneMensagens;
    @FXML private Button btnMensagens;
    @FXML private Button btnMensagensAbrir;
    @FXML private Button btnMensagensExcluir;
    @FXML private Button btnMensagensAprovar;
    @FXML private Button btnMensagensNegar;
    @FXML private TextField txtMensagensMensagem;
    @FXML private TextField txtMensagensRemetente;
    @FXML private TextField txtMensagensTitulo;
    @FXML private TableView<Notificacao> tabelaMensagens;
    @FXML private TableColumn<Notificacao, Pessoa> ColunaMensagensRemetente;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensLido;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensRespondido;
    @FXML private TableColumn<Notificacao, String> ColunaMensagensTitulo;
    
 /////////////////////////////////////////////////////////
    @FXML private Button btnLogout;
    @FXML private Text lblUsuario;
    @FXML private Font x1;
    @FXML private Button btnClientesNovoCliente;
    ObservableList<Cliente> observarClientes;
    ObservableList<Notificacao> observarMensagens;
    ObservableList<Conta> observarContas;
    @FXML private Text novaMensagem;
    
    
   
    
    ///////////STAGE//////////////
    private static Stage stage;         // criando stage, para armazenar tela em execução
    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        GerentePrincipal.stage = stage;
    }
    ///////////////////////////////////////////////////////////////////q
    ///////////////////EXTRATO /////////////// EXTRATO ////////////////////////////
    @FXML
    private void extrato() throws FileNotFoundException, DocumentException, IOException{
        if (txtClientesCpf.getText().equals("") && txtMensagensRemetente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
        }
        else{
            Cliente cliente;
        if(paneMensagens.isVisible())
            cliente = Banco.buscaNome(txtMensagensRemetente.getText());
        else
            cliente = Banco.buscaCpf(txtClientesCpf.getText());        
        
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
    }
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void excluirConta() throws Exception{
        Administrador a = Banco.getaAtual();
        Banco.removerAdmin(a);
        JOptionPane.showMessageDialog(null, "Você deletou sua conta!");
        GerentePrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    public void abrirPainelMeusDados() {
        limpar();
        paneMeusDados.setVisible(true);
        txtMeusDadosNome.setText(Banco.getaAtual().getNome());
        txtMeusDadosCpf.setText(Banco.getaAtual().getCpf());
        txtMeusDadosId.setText(Integer.toString(Banco.getaAtual().getId()));
        txtMeusDadosLogin.setText(Banco.getaAtual().getLogin());
        txtMeusDadosTelefone.setText(Banco.getaAtual().getTelefone());
        txtMeusDadosSenha.setText(Banco.getaAtual().getSenha());
        txtMeusDadosFuncao.setText(Banco.getaAtual().getFuncao());
        btnMeusDadosEditar.setDisable(false);
        btnMeusDadosConfirmar.setDisable(true);
        btnMeusDadosCancelar.setDisable(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    public void abrirPainelClientes() {
        limpar();
        limparTxtCliente();
        tabelaClientesInfor.refresh();
        paneClientes.setVisible(true);
        ColunaClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        ColunaClienteLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        observarClientes = FXCollections.observableArrayList();
        for(Cliente cliente : Banco.getListaClientes()){
            if(cliente.getGerente().getId()==Banco.getaAtual().getId()){
                observarClientes.add(cliente);
            }
        }
        tabelaClientesInfor.setItems(observarClientes);
        procurarNaTabelaCliente();
    }
    
    @FXML
    private void abrirPainelMensagens() {
        limpar();
        paneMensagens.setVisible(true);
        ColunaMensagensTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        ColunaMensagensRemetente.setCellValueFactory(new PropertyValueFactory<>("remetente"));
        ColunaMensagensLido.setCellValueFactory(new PropertyValueFactory<>("visualizado"));
        ColunaMensagensRespondido.setCellValueFactory(new PropertyValueFactory<>("respondido"));
        observarMensagens = FXCollections.observableArrayList(Banco.getaAtual().getListaNotificacoes());
        tabelaMensagens.setItems(observarMensagens);
        
    }
    
    @FXML
    public void limpar(){
        btnMensagensAbrir.setDisable(true);
        btnMensagensExcluir.setDisable(true);
        paneMeusDados.setVisible(false);
        paneClientes.setVisible(false);
        paneMensagens.setVisible(false);
        paneMensagensVisualizar.setVisible(false);
    }
    
    @FXML
    public void logout() throws Exception{
        GerentePrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    
    @FXML
    private void selecionarTabelaCliente(){
        limparTxtCliente();
        Cliente cliente = tabelaClientesInfor.getSelectionModel().getSelectedItem(); // pegando objeto
        preencherTabelaCliente(cliente);
        preencherTabelaContas(cliente);
    }
    @FXML
    private void selecionarTabelaMensagens(){
        btnMensagensAbrir.setDisable(false);
        btnMensagensExcluir.setDisable(false);
    }
    @FXML
    private void abrirMensagem(){
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        n.setVisualizado(true);
        if(Banco.getaAtual().verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
        else
            novaMensagem.setVisible(false);
        txtMensagensTitulo.setText(n.getTitulo());
        txtMensagensRemetente.setText(n.getRemetente().getNome());
        txtMensagensMensagem.setText(n.getMensagem());
        tabelaMensagens.refresh();
        paneMensagensVisualizar.setVisible(true);
        if(n.isRespondido()==false) {
            btnMensagensAprovar.setDisable(false);
            btnMensagensNegar.setDisable(false);
        } else {
            btnMensagensAprovar.setDisable(true);
            btnMensagensNegar.setDisable(true);
        }
        
    }
    @FXML
    private void excluirMensagem(){
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        Gerente gerente = (Gerente) Banco.getaAtual();
        gerente.excluirNotificacao(n);
        JOptionPane.showMessageDialog(null, "Mensagem excluida com sucesso!");
        if(Banco.getaAtual().verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
        else
            novaMensagem.setVisible(false);
        abrirPainelMensagens();
    }
    
    @FXML
    private void aprovarMensagem(){
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        n.setRespondido(true);
        n.setOk(true);
        btnMensagensAprovar.setDisable(true);
        btnMensagensNegar.setDisable(true);
        tabelaMensagens.refresh();
        
        if(n.getTipo().getTipo().equals("Conjunta")){
            Notificacao nova = new Notificacao("Aumento concedido!", "Em consulta a seu historico, achamos viável lhe conceder um aumento de 1000,00 Reais de limite!", n.getRemetente(), n.getDestinatario(), null);
            Cliente cliente = (Cliente) n.getRemetente();
            cliente.adicionarNotificacao(nova);
            Cliente cp = null;
                    if(cliente == cliente.getContaConjunta().getPrimeiroCliente()){
                        cp = cliente.getContaConjunta().getSegundoCliente();
                    }else
                        cp = cliente.getContaConjunta().getPrimeiroCliente();
                cp.adicionarNotificacao(nova);
            cliente.getContaConjunta().setLimiteTotal(cliente.getContaConjunta().getLimiteTotal()+1000);
            cliente.getContaConjunta().setLimite(cliente.getContaConjunta().getLimite()+1000);
            JOptionPane.showMessageDialog(null, "Resposta enviada ao Cliente!");
        } else {
            Notificacao nova = new Notificacao("Aumento concedido!", "Em consulta a seu historico, achamos viável lhe conceder um aumento de 1000,00 Reais de limite!", n.getRemetente(), n.getDestinatario(), null);
            Cliente cliente = (Cliente) n.getRemetente();
            cliente.adicionarNotificacao(nova);
            cliente.getContaCorrente().setLimiteTotal(cliente.getContaCorrente().getLimiteTotal()+1000);
            cliente.getContaCorrente().setLimite(cliente.getContaCorrente().getLimite()+1000);
            JOptionPane.showMessageDialog(null, "Resposta enviada ao Cliente!");
        }
        
    }
    
    @FXML
    private void negarMensagem() {
        Notificacao n = tabelaMensagens.getSelectionModel().getSelectedItem();
        n.setRespondido(true);
        btnMensagensAprovar.setDisable(true);
        btnMensagensNegar.setDisable(true);
        tabelaMensagens.refresh();
        Notificacao nova = new Notificacao("Aumento negado!", "Em consulta a seu historico, vimos que não há motivos para o aumento!", n.getRemetente(), n.getDestinatario(), null);
        Cliente cliente = (Cliente) n.getRemetente();
        cliente.adicionarNotificacao(nova);
        JOptionPane.showMessageDialog(null, "Resposta enviada ao Cliente!");
    }
    
    @FXML
    private void preencherTabelaContas(Cliente cliente){
    ColunaClienteTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        ColunaClienteConta.setCellValueFactory(new PropertyValueFactory<>("conta"));
        ColunaClienteAgencia.setCellValueFactory(new PropertyValueFactory<>("agencia"));
        ColunaClienteSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        observarContas = FXCollections.observableArrayList();
        if(cliente.isContaCorrenteOk())
            observarContas.add(cliente.getContaCorrente());
        if(cliente.isContaPoupancaOk())
            observarContas.add(cliente.getContaPoupanca());
        if(cliente.isContaConjuntaOk())
            observarContas.add(cliente.getContaConjunta());
        
        tabelaClientesContas.setItems(observarContas);
    }
    
    @FXML
    private void selecionarTabelaClientesContas() {
        Cliente cliente = tabelaClientesInfor.getSelectionModel().getSelectedItem();
        Conta conta = tabelaClientesContas.getSelectionModel().getSelectedItem();
        txtClientesAgencia.setText(Integer.toString(conta.getAgencia()));
        if (conta.getTipo().equals("Poupança"))
        txtClientesConta.setText("0"+Integer.toString(conta.getConta()));
        else
        txtClientesConta.setText(Integer.toString(conta.getConta()));
        txtClientesSaldo.setText(Double.toString(conta.getSaldo()));
        txtClientesTipo.setText(conta.getTipo());
    }
    
    @FXML
    private void procurarNaTabelaCliente(){
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Cliente> filteredData = new FilteredList<>(observarClientes, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        txtClientesBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                int id = 10;
                
                if (person.getNome().toLowerCase().contains(lowerCaseFilter) && (cbClientesBusca.getValue().equals("NOME"))) {
                    return true; 
                } else if (person.getCpf().toLowerCase().contains(lowerCaseFilter)&& (cbClientesBusca.getValue().equals("CPF"))) {
                    return true; 
                } /*else if (person.getId().contains(id) && (cbClientesBusca.getValue().equals("ID"))){
                    return true; 
                }*/                  // implementar por id

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabelaClientesInfor.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tabelaClientesInfor.setItems(sortedData);
    }
    
    
    @FXML
    private void editarMeusDados(){
        JOptionPane.showMessageDialog(null, "Use os campos de texto para editar!");
        txtMeusDadosNome.setEditable(true);
        txtMeusDadosCpf.setEditable(true);
        txtMeusDadosTelefone.setEditable(true);
        txtMeusDadosLogin.setEditable(true);
        txtMeusDadosSenha.setEditable(true);
        btnMeusDadosEditar.setDisable(true);
        btnMeusDadosCancelar.setDisable(false);
        btnMeusDadosConfirmar.setDisable(false);
    }
    @FXML
    private void confirmarEditarMeusDados(){
        Administrador diretor = Banco.buscaIdAdm(Integer.parseInt(txtMeusDadosId.getText()));
        diretor.setNome(txtMeusDadosNome.getText().toUpperCase());
        diretor.setCpf(txtMeusDadosCpf.getText());
        diretor.setTelefone(txtMeusDadosTelefone.getText());
        diretor.setLogin(txtMeusDadosLogin.getText());
        diretor.setSenha(txtMeusDadosSenha.getText());
        JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso!");
        abrirPainelMeusDados();
    }
    @FXML
    private void cancelarEditarMeusDados(){
        limparTxtMeusDados();
        abrirPainelMeusDados();
    }
    
    
    @FXML
    private void editarCliente(){
        JOptionPane.showMessageDialog(null, "Use os campos de texto para editar!");
        txtClientesNome.setEditable(true);
        txtClientesCpf.setEditable(true);
        txtClientesTelefone.setEditable(true);
        btnClientesEditar.setDisable(true);
        btnClientesExtrato.setDisable(true);
        btnClientesExcluir.setDisable(true);
        btnClientesCancelar.setDisable(false);
        btnClientesConfirmar.setDisable(false);
    }
    @FXML
    private void confirmarEditarCliente(){
        Cliente cliente = Banco.buscaId(Integer.parseInt(txtClientesId.getText()));
        cliente.setNome(txtClientesNome.getText().toUpperCase());
        cliente.setCpf(txtClientesCpf.getText());
        cliente.setTelefone(txtClientesTelefone.getText());
        JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso!");
        abrirPainelClientes();
    }
    
    @FXML
    private void cancelarEditarCliente(){
        limparTxtCliente();
    }
    
    @FXML
    private void buscarCliente(){
        if(cbClientesBusca.getValue().equals("ID")||(cbClientesBusca.getValue().equals("CPF"))||(cbClientesBusca.getValue().equals("NOME"))){
        Cliente cliente = null;
        if (cbClientesBusca.getValue().equals("ID")){
            cliente = Banco.buscaId(Integer.parseInt(txtClientesBusca.getText()));
        }else if (cbClientesBusca.getValue().equals("CPF")){
            cliente = Banco.buscaCpf(txtClientesBusca.getText());
        }else if(cbClientesBusca.getValue().equals("NOME")){
            cliente = Banco.buscaNome(txtClientesBusca.getText());
        }else{
            JOptionPane.showMessageDialog(null, "Selecione a opção de busca!");
        }
        if (cliente == null)
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!!");
        else{
            preencherTabelaCliente(cliente);
            preencherTabelaContas(cliente);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Escolha que item pesquisar!");
        }
    }
            
            
    @FXML
    private void preencherTabelaCliente(Cliente cliente){
        txtClientesNome.setText(cliente.getNome());
        txtClientesId.setText(Integer.toString(cliente.getId()));
        if(cliente.getGerente()!=null)
        txtClientesGerente.setText(cliente.getGerente().getNome());
        else
        txtClientesGerente.setText("Sem Gerentes");
        txtClientesCpf.setText(cliente.getCpf());
        txtClientesTelefone.setText(cliente.getTelefone());
        txtClientesLogin.setText(cliente.getLogin());
        txtClientesSenha.setText(cliente.getSenha());
        if(txtClientesCpf.getText().equals("")){
            btnClientesExcluir.setDisable(true);
            btnClientesEditar.setDisable(true);
            btnClientesExtrato.setDisable(true);
        }else{
           btnClientesExcluir.setDisable(false);
           btnClientesEditar.setDisable(false);
           btnClientesExtrato.setDisable(false);
        }
    }
    
    
    @FXML
    private void excluirCliente(){
       Cliente c = Banco.buscaCpf(txtClientesCpf.getText());
       if(Banco.removerCliente(c)){
           JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
       }else{
           JOptionPane.showMessageDialog(null, "Erro ao remover Cliente!");      
       }
       abrirPainelClientes();
       btnClientesExcluir.setDisable(true);
       limparTxtCliente();
    }
    
    
    @FXML
    private void limparTxtCliente(){
        txtClientesCpf.setText("");
        txtClientesCpf.setEditable(false);
        txtClientesNome.setText("");
        txtClientesNome.setEditable(false);
        txtClientesId.setText("");
        txtClientesGerente.setText("");
        txtClientesLogin.setText("");
        txtClientesSenha.setText("");
        txtClientesTelefone.setText("");
        txtClientesTelefone.setEditable(false);
        txtClientesSaldo.setText("");
        txtClientesConta.setText("");
        txtClientesTipo.setText("");
        txtClientesAgencia.setText("");
        txtClientesBusca.setText("");
        btnClientesExtrato.setDisable(true);
        btnClientesExcluir.setDisable(true);
        btnClientesEditar.setDisable(true);
        btnClientesCancelar.setDisable(true);
        btnClientesConfirmar.setDisable(true);
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
    private void abrirNovoCliente() throws IOException{
        Stage stageGerente = GerentePrincipal.getStage();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/NovoClienteView.fxml")); 
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        setStage(stage);
        stage.showAndWait();
        GerentePrincipal.setStage(stageGerente);
        abrirPainelClientes();
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lblUsuario.setText(Banco.getaAtual().getNome());
        cbClientesBusca.getItems().addAll("NOME","ID","CPF");
        cbClientesBusca.getSelectionModel().select(0);
        novaMensagem.setVisible(false);
        if(Banco.getaAtual().verificarNovaMensagem() == true)
            novaMensagem.setVisible(true);
        
    }    
    
}
