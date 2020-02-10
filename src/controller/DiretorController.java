package controller;

import classes.Administrador;
import classes.Banco;
import classes.Cliente;
import classes.Conta;
import classes.DiretorPrincipal;
import classes.Extrato;
import classes.Gerente;
import classes.Login;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;





public class DiretorController implements Initializable {
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
    
 // PANE ADMINISTRADORES ////////////////////////////////
    @FXML private Pane paneAdministradores;
    @FXML private TextField txtAdministradoresId;
    @FXML private TextField txtAdministradoresTelefone;
    @FXML private TextField txtAdministradoresNome;
    @FXML private TextField txtAdministradoresCpf;
    @FXML private TextField txtAdministradoresFuncao;
    @FXML private TextField txtAdministradoresLogin;
    @FXML private TextField txtAdministradoresBusca;
    @FXML private PasswordField txtAdministradoresSenha;
    @FXML private Button btnAdministradoresBuscar;
    @FXML private Button btnAdministradoresCancelar;
    @FXML private Button btnAdministradores;
    @FXML private Button btnAdministradoresConfirmar;
    @FXML private Button btnAdministradoresPdf;
    @FXML private Button btnAdministradoresExcluir;
    @FXML private Button btnAdministradoresEditar;
    @FXML private Button btnAdministradoresFechar;
    @FXML private Button btnAdministradoresNovoAdmin;
    @FXML private ComboBox<String> cbAdministradoresBusca;
    @FXML private TableView<Administrador> tabelaAdministradores;
    @FXML private TableColumn<Administrador, String> ColunaAdmTelefone;
    @FXML private TableColumn<Administrador, String> ColunaAdmNome;
    @FXML private TableColumn<Administrador, String> ColunaAdmId;
    @FXML private TableColumn<Administrador, String> ColunaAdmFuncao;
    @FXML private TableColumn<Administrador, String> ColunaAdmCpf;
    @FXML private TableColumn<Administrador, String> ColunaAdmLogin;
 // PANE CLIENTES  //////////////////////////////////////
    @FXML private ComboBox<Administrador> cbEditarGerente;
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
 /////////////////////////////////////////////////////////
    @FXML private Button btnLogout;
    //@FXML private Rectangle barraLateral; 
    @FXML private Text lblUsuario;
    @FXML private Font x1;
    @FXML private Button btnClientesNovoCliente;
    private ObservableList<Cliente> observarClientes;
    private ObservableList<Administrador> observarAdministradores;
    private ObservableList<Conta> observarContas;
    private ObservableList<Administrador> og;
    private List<Administrador> observarGerentes = new ArrayList<>();
    
    
    ///////////STAGE//////////////
    private static Stage stage;         // criando stage, para armazenar tela em execução
    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        DiretorPrincipal.stage = stage;
    }
    /////////////////PDF////////////////////PDF/////////////PDF///////////////////////
    @FXML
    private void pdf() throws FileNotFoundException, DocumentException, IOException{
        if (txtAdministradoresCpf.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Sem gerente selecionado!");
        }
        else{
        Administrador gerente = Banco.buscaCpfAdm(txtAdministradoresCpf.getText());        
        
        Document pdf = new Document() {}; 
        PdfWriter.getInstance(pdf, new FileOutputStream("Gerente.pdf")); // nome do arquivo
        pdf.open(); // abrindo o arquivo para poder editar
        pdf.add(new Paragraph("Número de cadastro: "+gerente.getId()));
        pdf.add(new Paragraph("Gerente: "+gerente.getNome()));
        pdf.add(new Paragraph("CPF: "+gerente.getCpf()));
        pdf.add(new Paragraph("Telefone: "+gerente.getTelefone()));
        pdf.add(new Paragraph("Login: "+gerente.getLogin()));
        pdf.add(new Paragraph("\n\nResponsável pelos seguintes clientes: \n\n" ));
        
        PdfPTable table = new PdfPTable(3); // criando tabela dentro do arquivo
        PdfPCell id = new PdfPCell(new Paragraph("ID"));
        PdfPCell cliente = new PdfPCell(new Paragraph("CLIENTE"));
        PdfPCell cpf = new PdfPCell(new Paragraph("CPF"));
        
        table.addCell(id);
        table.addCell(cliente);
        table.addCell(cpf);
        
        for (Cliente c : Banco.getListaClientes()){
            if(c.getGerente().getNome().equals(gerente.getNome())){
            id = new PdfPCell(new Paragraph(c.getId()+""));
            cliente = new PdfPCell(new Paragraph(c.getNome()+""));
            cpf = new PdfPCell(new Paragraph(c.getCpf()+""));
        
            table.addCell(id);
            table.addCell(cliente);
            table.addCell(cpf);
            }
        }
        pdf.add(table);
        pdf.close();
        
        Desktop.getDesktop().open(new File("Gerente.pdf"));
        }
    }
 ///////////////////EXTRATO /////////////// EXTRATO ////////////////////////////
    @FXML
    private void extrato() throws FileNotFoundException, DocumentException, IOException{
        if (txtClientesCpf.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
        }
        else{
        Cliente cliente = Banco.buscaCpf(txtClientesCpf.getText());        
        
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
        DiretorPrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    
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
        if(Banco.getaAtual().getLogin().equals("root"))
            btnMeusDadosEditar.setDisable(true);
        else
            btnMeusDadosEditar.setDisable(false);
        btnMeusDadosConfirmar.setDisable(true);
        btnMeusDadosCancelar.setDisable(true);
        
    }
    @FXML
    public void abrirPainelAdministradores() {
        limpar();
        limparTxtAdmin();
        tabelaAdministradores.refresh();
        paneAdministradores.setVisible(true);
        ColunaAdmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaAdmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaAdmCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        ColunaAdmLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        ColunaAdmFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        ColunaAdmTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        observarAdministradores = FXCollections.observableArrayList(Banco.getListaAdm());
        tabelaAdministradores.setItems(observarAdministradores);
        procurarNaTabelaAdm();
    }
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
        observarClientes = FXCollections.observableArrayList(Banco.getListaClientes());
        tabelaClientesInfor.setItems(observarClientes);
        procurarNaTabelaCliente();
       // buscaIdNaTabela();
    }
    
    @FXML
    public void limpar(){
        paneMeusDados.setVisible(false);
        paneAdministradores.setVisible(false);
        paneClientes.setVisible(false);
    }
    @FXML
    public void logout() throws Exception{
        DiretorPrincipal.getStage().close();
        Login l = new Login();
        l.start(new Stage());
    }
    
    @FXML
    private void selecionarTabelaCliente(){
        limparTxtCliente();
        Cliente cliente = tabelaClientesInfor.getSelectionModel().getSelectedItem();
        preencherTabelaCliente(cliente);
        preencherTabelaContas(cliente);
    }
    
    @FXML
    private void selecionarTabelaClienteContas() {
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
    private void selecionarTabelaAdministradores(){
        limparTxtAdmin();
        Administrador adm = tabelaAdministradores.getSelectionModel().getSelectedItem();
        preencherTabelaAdm(adm);
    }
    
   /* @FXML
    private ObservableList<Cliente> buscaIdNaTabela(){
        ObservableList<Cliente> listaBusca = FXCollections.observableArrayList();
        return listaBusca;
    }*/
    
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
                //int id = 10;
                
                
                if (person.getNome().toLowerCase().contains(lowerCaseFilter) && (cbClientesBusca.getValue().equals("NOME"))) {
                    return true; 
                } else if (person.getCpf().toLowerCase().contains(lowerCaseFilter)&& (cbClientesBusca.getValue().equals("CPF"))) {
                    return true; 
                }             

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
    private void procurarNaTabelaAdm(){
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Administrador> filteredData = new FilteredList<>(observarAdministradores, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        txtAdministradoresBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNome().toLowerCase().contains(lowerCaseFilter) && (cbAdministradoresBusca.getValue().equals("NOME"))) {
                    return true; 
                } else if (person.getCpf().toLowerCase().contains(lowerCaseFilter)&& (cbAdministradoresBusca.getValue().equals("CPF"))) {
                    return true; 
                }
                // implementar por id
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Administrador> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabelaAdministradores.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tabelaAdministradores.setItems(sortedData);
        
            
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
        if(Banco.veririficarGerente()){
            cbEditarGerente.setVisible(true);
            txtClientesGerente.setVisible(false);
        
            for(Administrador a : Banco.getListaAdm()){
               if (a.getFuncao().equals("GERENTE"))
                observarGerentes.add(a);
            }
            og = FXCollections.observableArrayList(observarGerentes);
            cbEditarGerente.setItems(og);
            cbEditarGerente.getSelectionModel().select(0);
        }    
                
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
        cliente.setGerente((Gerente) cbEditarGerente.getSelectionModel().getSelectedItem());
        JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso!");
        abrirPainelClientes();
    }
    @FXML
    private void cancelarEditarCliente(){
        limparTxtCliente();
    }
    @FXML
    private void editarGerente(){
        JOptionPane.showMessageDialog(null, "Use os campos de texto para editar!");
        txtAdministradoresNome.setEditable(true);
        txtAdministradoresCpf.setEditable(true);
        txtAdministradoresTelefone.setEditable(true);
        txtAdministradoresFuncao.setEditable(true);
        btnAdministradoresEditar.setDisable(true);
        btnAdministradoresPdf.setDisable(true);
        btnAdministradoresExcluir.setDisable(true);
        btnAdministradoresCancelar.setDisable(false);
        btnAdministradoresConfirmar.setDisable(false);
    }
    @FXML
    private void confirmarEditarGerente(){
        Gerente gerente = (Gerente) Banco.buscaIdAdm(Integer.parseInt(txtAdministradoresId.getText()));
        gerente.setNome(txtAdministradoresNome.getText().toUpperCase());
        gerente.setCpf(txtAdministradoresCpf.getText());
        gerente.setTelefone(txtAdministradoresTelefone.getText());
        gerente.setFuncao(txtAdministradoresFuncao.getText().toUpperCase());
        JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso!");
        abrirPainelAdministradores();
    }
    @FXML
    private void cancelarEditarGerente(){
        limparTxtAdmin();
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
    private void buscarAdm(){
        Administrador adm = null;
        if (cbAdministradoresBusca.getValue().equals("ID")){
            adm = Banco.buscaIdAdm(Integer.parseInt(txtAdministradoresBusca.getText()));
        }else if (cbAdministradoresBusca.getValue().equals("CPF")){
            adm = Banco.buscaCpfAdm(txtAdministradoresBusca.getText());
        }else if(cbAdministradoresBusca.getValue().equals("NOME")){
            adm = Banco.buscaNomeAdm(txtAdministradoresBusca.getText());
        }else{
            JOptionPane.showMessageDialog(null, "Selecione a opção de busca!");
        }
        if (adm == null)
            JOptionPane.showMessageDialog(null, "Administrador não encontrado!!");
        else
            preencherTabelaAdm(adm);
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
    private void preencherTabelaAdm(Administrador adm){
        txtAdministradoresNome.setText(adm.getNome());
        txtAdministradoresId.setText(Integer.toString(adm.getId()));
        txtAdministradoresFuncao.setText(adm.getFuncao());
        txtAdministradoresCpf.setText(adm.getCpf());
        txtAdministradoresTelefone.setText(adm.getTelefone());
        txtAdministradoresLogin.setText(adm.getLogin());
        txtAdministradoresSenha.setText(adm.getSenha());
        if(txtAdministradoresFuncao.getText().equals("GERENTE")){
            btnAdministradoresExcluir.setDisable(false);
            btnAdministradoresPdf.setDisable(false);
            btnAdministradoresEditar.setDisable(false);
        } else {
            btnAdministradoresExcluir.setDisable(true);
            btnAdministradoresPdf.setDisable(true);
            btnAdministradoresEditar.setDisable(true);
        }
    }
    
    @FXML
    private void excluirAdmin(){
        Administrador adm = Banco.buscaCpfAdm(txtAdministradoresCpf.getText());
        
        if (Banco.removerAdmin(adm))
        JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
        else
        JOptionPane.showMessageDialog(null, "Erro ao deletar usuário!");
        
        if(!Banco.veririficarGerente()){
            for(Cliente c : Banco.getListaClientes()){
                c.setGerente(null);
            }
        }
        abrirPainelAdministradores();
        btnAdministradoresExcluir.setDisable(true);
        limparTxtAdmin();
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
    private void limparTxtAdmin(){
        txtAdministradoresCpf.setText("");
        txtAdministradoresCpf.setEditable(false);
        txtAdministradoresNome.setText("");
        txtAdministradoresNome.setEditable(false);
        txtAdministradoresFuncao.setText("");
        txtAdministradoresFuncao.setEditable(false);
        txtAdministradoresId.setText("");
        txtAdministradoresLogin.setText("");
        txtAdministradoresSenha.setText("");
        txtAdministradoresTelefone.setText("");
        txtAdministradoresTelefone.setEditable(false);
        txtAdministradoresBusca.setText("");
        btnAdministradoresPdf.setDisable(true);
        btnAdministradoresExcluir.setDisable(true);
        btnAdministradoresEditar.setDisable(true);
        btnAdministradoresCancelar.setDisable(true);
        btnAdministradoresConfirmar.setDisable(true);
        btnAdministradoresCancelar.setDisable(true);
        btnAdministradoresConfirmar.setDisable(true);
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
        cbEditarGerente.setVisible(false);
        txtClientesGerente.setVisible(true);
        observarGerentes.clear();
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
        Stage stageDiretor = DiretorPrincipal.getStage();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/NovoClienteView.fxml")); 
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        setStage(stage);
        stage.showAndWait();
        DiretorPrincipal.setStage(stageDiretor);
        abrirPainelClientes();
    }
    
    @FXML
    private void abrirNovoAdm() throws IOException{
        Stage stageDiretor = DiretorPrincipal.getStage();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/NovoAdminView.fxml")); 
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Novo Admin");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        setStage(stage);
        stage.showAndWait();
        DiretorPrincipal.setStage(stageDiretor);
        abrirPainelAdministradores();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUsuario.setText(Banco.getaAtual().getNome());
        
        cbClientesBusca.getItems().addAll("NOME","ID","CPF");
        cbAdministradoresBusca.getItems().addAll("NOME","ID","CPF");
        cbClientesBusca.getSelectionModel().select(0);
        cbAdministradoresBusca.getSelectionModel().select(0);
        if(Banco.getaAtual().getLogin().equals("root"))
            btnMeusDadoseExcluirMinhaConta.setDisable(true);
        
    }
    
}
