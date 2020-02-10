package classes;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DiretorPrincipal extends Application {
    
    public static Stage stage;         // criando stage, para armazenar tela em execução

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DiretorPrincipal.stage = stage;
    }
    
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/views/DiretorView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
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
