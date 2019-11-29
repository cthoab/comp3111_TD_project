/*
package MainProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

public class MyControllerTest extends Application{

    MyController myController;

    @Test
    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        myController = loader.getController();
        myController.createArena();
        myController.play();
        for (int i = 0; i < 20 ; i++) {
            myController.nextFrame();
        }
        Assert.assertEquals(myController.MonsterLabel.size(), 21);
    }
}
*/



//This testing is not work!! need to be fixed!!