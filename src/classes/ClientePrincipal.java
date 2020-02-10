package classes;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientePrincipal extends Application {
    
    private static Stage stage;         // criando stage, para armazenar tela em execução

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ClientePrincipal.stage = stage;
    }
    
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/views/ClienteView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        setStage(stage); // setando a tela inicial no stage
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
