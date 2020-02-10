/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nidson
 */
public class GerentePrincipal extends Application {
    
    public static Stage stage;         // criando stage, para armazenar tela em execução

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GerentePrincipal.stage = stage;
    }
    
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/views/GerenteView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("KEROCASH - ADM GERENTE");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        setStage(stage); // setando a tela inicial no stage
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
