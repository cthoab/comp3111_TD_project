package MainProgram;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

import Arena.Arena;
import MapElement.Monster.*;
import javafx.scene.shape.Circle;

public class MyController {
    @FXML
    private Button buttonNextFrame;

    @FXML
    private Button buttonSimulate;

    @FXML
    private Button buttonPlay;

    @FXML
    private AnchorPane paneArena;

    @FXML
    private Label labelBasicTower;

    @FXML
    private Label labelIceTower;

    @FXML
    private Label labelCatapult;

    @FXML
    private Label labelLaserTower;

    @FXML
    private Label labelResource;

    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 480;
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;
    Boolean clicked = false;
    Circle circle = new Circle();

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    private int x = -1, y = 0; //where is my monster

    private Arena arena;
    private ArrayList<Label> MonsterLabel = new ArrayList<Label>();

    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        System.out.println("Play button clicked");
        arena.spawnMonster();
        drawArena(arena);
    }

    /**
     * A function that create the Arena
     */
    @FXML
    public void createArena() {
        arena = new Arena();
        if (grids[0][0] != null)
            return; //created already
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                Label newLabel = new Label();
                if (isGreen(i,j)) {
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

                    newLabel.setOnMouseClicked(event -> {
                        if(newLabel.getText()!="Drop\nHere") { //clicking tower

                            System.out.println(newLabel.getText() + " clicking");
                                if(!clicked) {
                                    circle.setCenterX(newLabel.getLayoutX()+GRID_WIDTH/2);
                                    circle.setCenterY(newLabel.getLayoutY()+GRID_HEIGHT/2);
                                    circle.setRadius(2 * 40);
                                    circle.setFill(Color.TRANSPARENT);
                                    circle.setStroke(Color.GREEN);
                                    circle.setMouseTransparent(true);
                                    circle.setVisible(true);
                                    circle.managedProperty().bind(circle.visibleProperty());
                                    System.out.println("clicked");
                                    clicked = true;
                                }
                                else {
                                    circle.setVisible(false);
                                    clicked = false;
                                }

                        }
                        else
                        {
                            circle.setVisible(false);
                            circle.managedProperty().bind(circle.visibleProperty());
                            clicked = false;
                            System.out.println(newLabel.getText() + " clicking");
                        }
                    });


                }
                else
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                newLabel.setLayoutX(j * GRID_WIDTH);
                newLabel.setLayoutY(i * GRID_HEIGHT);
                newLabel.setMinWidth(GRID_WIDTH);
                newLabel.setMaxWidth(GRID_WIDTH);
                newLabel.setMinHeight(GRID_HEIGHT);
                newLabel.setMaxHeight(GRID_HEIGHT);
                newLabel.setStyle("-fx-border-color: black;");
                grids[i][j] = newLabel;
                paneArena.getChildren().addAll(newLabel);
                if(isGreen(i,j))
                    setDragAndDrop(i, j);
            }
            Label resource = labelResource;
            resource.setText(Integer.toString(arena.Resources));
            paneArena.getChildren().add(circle);
    }

    @FXML
    private void nextFrame() {
        arena.monsterMove();
        arena.spawnMonster();
        drawArena(arena);
        if(arena.checkGameOver())
            gameOver();
    }

    private void drawArena(Arena a){
        for(Label m : MonsterLabel){
            paneArena.getChildren().removeAll(m);
        }
        for(Monster m : a.monsters){
            Label newLabel = new Label();
            newLabel.setLayoutX(m.getX_position()-10);
            newLabel.setLayoutY(m.getY_position()-10);
            newLabel.setMinWidth(GRID_WIDTH / 2);
            newLabel.setMaxWidth(GRID_WIDTH / 2);
            newLabel.setMinHeight(GRID_WIDTH / 2);
            newLabel.setMaxHeight(GRID_WIDTH / 2);
            newLabel.setStyle("-fx-border-color: none;");
            Label info = new Label();
            newLabel.setOnMouseEntered(e->{
                info.setText("HP: " + Integer.toString(m.getHP()));
                info.setLayoutX(m.getX_position()+10);
                info.setLayoutY(m.getY_position()-10);
                info.setStyle("-fx-background-color: yellow; -fx-font: 20 arial");
                info.setMinHeight(30);
                info.setMinWidth(40);
                paneArena.getChildren().add(info);
            });
            newLabel.setOnMouseExited(e->{
                paneArena.getChildren().remove(info);
            });
            Image image;
            if(m.getClass() == Fox.class)
                image = new Image("file:src/main/resources/fox20x20.png");
            else if(m.getClass() == Penguin.class)
                image = new Image("file:src/main/resources/penguin20x20.png");
            else
                image = new Image("file:src/main/resources/unicorn20x20.png");
            newLabel.setGraphic(new ImageView(image));
            MonsterLabel.add(newLabel);
            paneArena.getChildren().addAll(newLabel);
        }
    }

    public void gameOver(){
        System.out.println("Gameover");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Over!");
        alert.showAndWait();
        //TODO disable event handlers
    }

    /**
     * A function that demo how drag and drop works
     */
    private boolean isGreen(int row, int col){

        return !(col % 2 == 0 || row == ((col + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1));
    }

    private void setDragAndDrop(int row, int col) {
        Label target = grids[row][col];
        target.setText("Drop\nHere");
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        Label source3 = labelCatapult;
        Label source4 = labelLaserTower;
        double orgSceneX, orgSceneY;
        double orgTranslateX, orgTranslateY;
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));
        source3.setOnDragDetected(new DragEventHandler(source3));
        source4.setOnDragDetected(new DragEventHandler(source4));
        target.setOnDragDropped(new DragDroppedEventHandler());
        //well, you can also write anonymous class or even lambda
        //Anonymous class
        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    target.setStyle("-fx-border-color: blue;");
                }

                event.consume();
            }
        });
        //lambda
        target.setOnDragExited((event) -> {
            /* mouse moved away, remove the graphical cues */
            target.setStyle("-fx-border-color: black;");
            System.out.println("Exit");
            event.consume();
        });
    }
}

class DragEventHandler implements EventHandler<MouseEvent> {
    private Label source;
    public DragEventHandler(Label e) {
        source = e;
    }
    @Override
    public void handle (MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);

        event.consume();
    }
}
class DragDroppedEventHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        System.out.println("xx");
        Dragboard db = event.getDragboard();
        boolean success = false;
        System.out.println(db.getString());
        if (db.hasString()) {
            ((Label)event.getGestureTarget()).setText(db.getString());
            success = true;
            Image image;
            switch (db.getString()){
                case "Basic Tower":
                    image = new Image("file:src/main/resources/basicTower40x40.png");
                    ((Label)event.getGestureTarget()).setGraphic(new ImageView(image));
                    break;
                case "Ice Tower":
                    image = new Image("file:src/main/resources/iceTower40x40.png");
                    ((Label)event.getGestureTarget()).setGraphic(new ImageView(image));
                    break;
                case "Catapult":
                    image = new Image("file:src/main/resources/catapult40x40.png");
                    ((Label)event.getGestureTarget()).setGraphic(new ImageView(image));
                    break;
                case "Laser Tower":
                    image = new Image("file:src/main/resources/laserTower40x40.png");
                    ((Label)event.getGestureTarget()).setGraphic(new ImageView(image));
                    break;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

}
