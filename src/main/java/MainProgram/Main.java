package MainProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * The class that start the JavaFx functions
 */
public class Main extends Application {

    /**
     * The caller of the whole program
     * @param args the default param
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start function of the JavaFx
     * @param primaryStage the stage object of the game
     * @throws Exception Throw exception if the FXML file is not available
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        Pane pane = new Pane(root);
        primaryStage.setTitle("Tower Defence");
        primaryStage.setScene(new Scene(pane, 600, 480));
        primaryStage.show();
        MyController appController = (MyController) loader.getController();
        appController.createArena();
    }
}
