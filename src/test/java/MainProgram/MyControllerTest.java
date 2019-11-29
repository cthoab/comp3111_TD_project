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

///**
// *
// * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
// *
// */
package MainProgram;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class MyControllerTest extends ApplicationTest {

	private Scene s;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        MyController appController = (MyController)loader.getController();
        appController.createArena();
	}


	@Test
	public void testPlayButton() {
		clickOn("#buttonPlay");
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if (h.getLayoutX() == 20 && h.getLayoutY() == 20)
					Assert.assertEquals(h.getText(), "M");
			}
		}
	}
	@Test
	public void testNextFrameButton() {
		clickOn("#buttonNextFrame");
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if (h.getLayoutX() == 20 && h.getLayoutY() == 20)
					Assert.assertEquals(h.getText(), "M");
			}
		}
	}

	@Test
	public void testDrag(){
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		javafx.scene.Node j = null;
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				if(i.getLayoutY()==40 && i.getLayoutY()==40)
					j = i;
			}
		}
		drag("#labelBasicTower").dropTo(j);
	}
}