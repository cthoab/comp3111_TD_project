package MainProgram;

import Arena.Arena;
import MapElement.Monster.Fox;
import MapElement.Monster.Monster;
import MapElement.Monster.Penguin;
import MapElement.Tower.Catapult;
import MapElement.Tower.IceTower;
import MapElement.Tower.LaserTower;
import MapElement.Tower.Tower;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

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
    private AnchorPane controlPanel;

    @FXML
    private Label labelBasicTower;

    @FXML
    private Label labelIceTower;

    @FXML
    private Label labelCatapult;

    @FXML
    private Label labelLaserTower;

    @FXML
    private Label labelResource = new Label();

    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 480;
    protected static final int GRID_WIDTH = 40;
    protected static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;
    boolean clicked = false;
    Circle circle_outer = new Circle();
    Circle circle_inner = new Circle();
    Label infoLabel = new Label();


    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    private int x = -1, y = 0; //where is my monster

    private Arena arena;
    private ArrayList<Label> MonsterLabel = new ArrayList<>();

    public static class Stone extends Circle{
        int damage;
        Stone(int center_x, int center_y, int damage){
            this.damage = damage;
            this.setCenterX(center_x);
            this.setCenterY(center_y);
            this.setRadius(25);
            this.setFill(Color.YELLOW);
            this.setVisible(true);
        }
    }
    public static class Laser extends Line{
        int damage;
        Laser(int from_x,int from_y,int to_x,int to_y,int damage){
            this.damage = damage;
            this.setStartX(from_x);
            this.setStartY(from_y);
            this.setEndX(to_x);
            this.setEndY(to_y);
            this.setStrokeWidth(3);
            this.setStroke(Color.RED);
        }
    }

    public static ArrayList<Stone> StoneCircle = new ArrayList<>();                  //when stone is thrown, transparent circle will be created and empty again after damage is calculate
    public static ArrayList<Laser> LaserLine = new ArrayList<>();                      //draw line between laser tower and the monster, then calculate damage to the monster on the line

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
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        Label source3 = labelCatapult;
        Label source4 = labelLaserTower;
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));
        source3.setOnDragDetected(new DragEventHandler(source3));
        source4.setOnDragDetected(new DragEventHandler(source4));

        labelResource.textProperty().bind(arena.Resources.asString());
        labelResource.setLayoutX(17);
        labelResource.setLayoutY(409);
        controlPanel.getChildren().add(labelResource);


        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                Label newLabel = new Label();
                if (isGreen(i,j)) {
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

                    newLabel.setOnMouseClicked(event -> {
                        if(newLabel.getText()!="Drop\nHere") { //clicking tower
                            System.out.println(newLabel.getText() + " clicking");
                        }
                        else
                        {
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
//            Label resource = labelResource;
//            resource.setText(Integer.toString(arena.Resources.get()));
            paneArena.getChildren().add(circle_outer);
            paneArena.getChildren().add(circle_inner);
    }

    @FXML
    private void nextFrame() {
        System.out.println("\n \n ------- Next Frame -------");
        paneArena.getChildren().removeAll(LaserLine);
        LaserLine.clear();
        arena.resetTowers();
        arena.removeDeadMonsters();
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
                info.setText("HP: " + m.getHP() +"/" + m.getMaxHP());
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
            if(m.getHP() <= 0)
                image = new Image("file:src/main/resources/collision20x20.png");
            else if(m.getClass() == Fox.class)
                image = new Image("file:src/main/resources/fox20x20.png");
            else if(m.getClass() == Penguin.class)
                image = new Image("file:src/main/resources/penguin20x20.png");
            else
                image = new Image("file:src/main/resources/unicorn20x20.png");
            newLabel.setGraphic(new ImageView(image));
            MonsterLabel.add(newLabel);
            paneArena.getChildren().addAll(newLabel);
        }
        paneArena.getChildren().addAll(LaserLine);
        paneArena.getChildren().addAll(StoneCircle);
    }

    public static void DrawLaser(int from_x, int from_y, int to_x, int to_y, int damage){
        Laser Laser = new Laser(from_x,from_y,to_x,to_y,damage);
        LaserLine.add(Laser);
    }

    public static void throwStone(int center_x, int center_y, int damage){
        StoneCircle.add(new Stone(center_x, center_y, damage));
    }

    public static void aoeDamage(Arena arena){
        System.out.println("\n------- Range Attack -------");
        for (Monster monster: arena.monsters) {
            for (Laser laser: LaserLine)
                if (laser.contains(monster.getX_position(), monster.getY_position())) {
                    monster.setHP(monster.getHP() - laser.damage);
                    System.out.println(monster.simpleInfo() + "is hit by the laser and cause " + laser.damage + " HP damage!");
                }
            for (Stone stone: StoneCircle)
                if (stone.contains(monster.getX_position(), monster.getY_position())) {
                    monster.setHP(monster.getHP() - stone.damage);
                    System.out.println(monster.simpleInfo() + "is hit by the stone cause " + stone.damage + " HP damage!");
                }
        }
        StoneCircle.clear();
    }

    public void gameOver(){
        System.out.println("Game Over");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Over!");
        for(Label[] Grids : grids)
            for(Label grid : Grids){
                grid.setOnMouseClicked(null);
                grid.setOnDragDetected(null);
                grid.setContextMenu(null);
                grid.setOnMouseEntered(null);
            }
        for (Label monster: MonsterLabel){
            monster.setOnMouseEntered(null);
        }
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
//        Label source1 = labelBasicTower;
//        Label source2 = labelIceTower;
//        Label source3 = labelCatapult;
//        Label source4 = labelLaserTower;
////        double orgSceneX, orgSceneY;
////        double orgTranslateX, orgTranslateY;
//        source1.setOnDragDetected(new DragEventHandler(source1));
//        source2.setOnDragDetected(new DragEventHandler(source2));
//        source3.setOnDragDetected(new DragEventHandler(source3));
//        source4.setOnDragDetected(new DragEventHandler(source4));
        target.setOnDragDropped(new DragDroppedEventHandler(arena,paneArena));
        //well, you can also write anonymous class or even lambda
        //Anonymous class
        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                //System.out.println("onDragOver");

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
                //System.out.println("onDragEntered");
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
            //System.out.println("Exit");
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

class ShowClickMenuHandler implements EventHandler<MouseEvent>{

    Label target;
    Arena arena;
    AnchorPane anchorPane;
    MenuItem UpgradeTower = new MenuItem("Upgrade the Tower");
    MenuItem DestroyTower = new MenuItem("Destroy The Tower");


    public ShowClickMenuHandler(Label target, Arena arena, AnchorPane anchorPane){
        this.target = target; this.arena = arena; this.anchorPane = anchorPane;
    }

    @Override
    public void handle(MouseEvent event) {
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(UpgradeTower,DestroyTower);
        UpgradeTower.setOnAction(e->{
            Alert alert;
            if(arena.UpgradeTower((int)target.getLayoutX()+MyController.GRID_WIDTH/2,(int)target.getLayoutY()+MyController.GRID_HEIGHT/2))
                alert = new Alert(Alert.AlertType.INFORMATION, "Upgraded!");
            else
                alert = new Alert(Alert.AlertType.INFORMATION,"Not enough resources!");
            alert.showAndWait();
            System.out.println(arena.Resources);
            System.out.println(arena.TowerInfo((int)target.getLayoutX()+MyController.GRID_WIDTH/2,(int)target.getLayoutY()+MyController.GRID_HEIGHT/2));
        });
        DestroyTower.setOnAction(e->{
            target.setOnMouseEntered(null);
            target.setContextMenu(null);
            target.setGraphic(null);
            target.setText("Drop\nHere");
            target.setOnDragDropped(new DragDroppedEventHandler(arena,anchorPane));
            target.setOnDragEntered(event1 -> {
                System.out.println("onDragEntered");
                if (event1.getGestureSource() != target &&
                        event1.getDragboard().hasString()) {
                    target.setStyle("-fx-border-color: blue;");
                }
                event1.consume();
            });
            target.setOnDragExited((event2) -> {
                /* mouse moved away, remove the graphical cues */
                target.setStyle("-fx-border-color: black;");
                System.out.println("Exit");
                event2.consume();
            });
            arena.RemoveTower((int)target.getLayoutX()+MyController.GRID_WIDTH/2,(int)target.getLayoutY()+MyController.GRID_HEIGHT/2);
        });
        target.setContextMenu(menu);
    }
}



class DragDroppedEventHandler implements EventHandler<DragEvent> {
    Arena arena;
    AnchorPane anchorPane;
    Label InfoLabel;
    Circle Inner = new Circle();
    Circle Outer = new Circle();

    DragDroppedEventHandler(Arena arena, AnchorPane anchorPane){
        this.arena = arena;
        this.anchorPane = anchorPane;
    }

    private void setUpTowerInfoLabel(Label Target, int position_x, int position_y){
        InfoLabel = new Label();  //Problem here
        Target.setOnMouseEntered(
                new MouseEnterShowInfoHandler(
                        anchorPane, InfoLabel, Target, arena.TowerAt(position_x,position_y),Inner,Outer));
        Target.setOnMouseExited(new MouseExitDismissInfoHandler(anchorPane,Inner,Outer,InfoLabel));
    }

    private void ShowAlert(int position_x, int position_y){
        Alert alert;
        if (arena.TowerInfo(position_x,position_y) != null)
            alert = new Alert(Alert.AlertType.INFORMATION, "You can not build tower on an existing tower!");
        else
            alert = new Alert(Alert.AlertType.INFORMATION, "Not enough resources to build");
        alert.showAndWait();
    }

    @Override
    public void handle(DragEvent event) {
        System.out.println("xx");
        Dragboard db = event.getDragboard();
        boolean success = false;
        System.out.println(db.getString());
        if (db.hasString()) {
            success = true;
            Image image;
            int position_x = (int) ((Label) event.getGestureTarget()).getLayoutX() + MyController.GRID_WIDTH/2;
            int position_y = (int) ((Label) event.getGestureTarget()).getLayoutY() + MyController.GRID_HEIGHT/2;
            switch (db.getString()){
                case "Basic Tower":
                    if(arena.BuildTower('B',position_x,position_y)) {
                        ((Label)event.getGestureTarget()).setText(db.getString());
                        image = new Image("file:src/main/resources/basicTower40x40.png");
                        ((Label) event.getGestureTarget()).setGraphic(new ImageView(image));
                        setUpTowerInfoLabel((Label) event.getGestureTarget(),position_x,position_y);
                        ((Label) event.getGestureTarget()).setOnMouseClicked(new ShowClickMenuHandler((Label) event.getGestureTarget(),arena,anchorPane));
                    }
                    else ShowAlert(position_x, position_y);
                    break;
                case "Ice Tower":
                    if(arena.BuildTower('I',position_x ,position_y)) {
                        ((Label)event.getGestureTarget()).setText(db.getString());
                        image = new Image("file:src/main/resources/iceTower40x40.png");
                        ((Label) event.getGestureTarget()).setGraphic(new ImageView(image));
                        setUpTowerInfoLabel((Label) event.getGestureTarget(),position_x,position_y);
                        ((Label) event.getGestureTarget()).setOnMouseClicked(new ShowClickMenuHandler((Label) event.getGestureTarget(),arena,anchorPane));
                    }
                    else ShowAlert(position_x,position_y);
                    break;
                case "Catapult":
                    if(arena.BuildTower('C',position_x,position_y)) {
                        ((Label)event.getGestureTarget()).setText(db.getString());
                        image = new Image("file:src/main/resources/catapult40x40.png");
                        ((Label) event.getGestureTarget()).setGraphic(new ImageView(image));
                        setUpTowerInfoLabel((Label) event.getGestureTarget(),position_x,position_y);
                        ((Label) event.getGestureTarget()).setOnMouseClicked(new ShowClickMenuHandler((Label) event.getGestureTarget(),arena,anchorPane));
                    }
                    else ShowAlert(position_x,position_y);
                    break;
                case "Laser Tower":
                    if(arena.BuildTower('L',position_x,position_y)) {
                        ((Label)event.getGestureTarget()).setText(db.getString());
                        image = new Image("file:src/main/resources/laserTower40x40.png");
                        ((Label) event.getGestureTarget()).setGraphic(new ImageView(image));
                        setUpTowerInfoLabel((Label) event.getGestureTarget(),position_x,position_y);
                        ((Label) event.getGestureTarget()).setOnMouseClicked(new ShowClickMenuHandler((Label) event.getGestureTarget(),arena,anchorPane));
                    }
                    else ShowAlert(position_x,position_y);
                    break;
            }
        }
//        System.out.println("Resources left: "+ arena.Resources);
//        LabelResources.setText(String.valueOf(arena.Resources));
        event.setDropCompleted(success);
        event.consume();
    }
}

class MouseEnterShowInfoHandler implements EventHandler<MouseEvent>{
    AnchorPane anchorPane;
    Label infoLabel;
    Label thisLabel;
    Tower tower;
    int range;
    Circle circle_outer;
    Circle circle_inner;

    private void DrawCircle (Circle circle, double center_x,double center_y, int radius, boolean visible){
        circle.setCenterX(center_x);
        circle.setCenterY(center_y);
        circle.setRadius(radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.GREEN);
        circle.setMouseTransparent(true);
        circle.setVisible(visible);
        circle.managedProperty().bind(circle.visibleProperty());
    }


    MouseEnterShowInfoHandler(AnchorPane anchorPane, Label infoLabel, Label thisLabel, Tower tower, Circle inner, Circle outer){
        this.anchorPane = anchorPane;
        this.infoLabel = infoLabel;
        this.thisLabel = thisLabel;
        this.tower = tower;
        this.range = tower.getRange();
        this.circle_inner = inner;
        this.circle_outer = outer;
    }

    @Override
    public void handle(MouseEvent event) {
        infoLabel.setText(tower.TowerToString());
        infoLabel.setLayoutX(thisLabel.getLayoutX()+40);
        infoLabel.setLayoutY(thisLabel.getLayoutY());
        infoLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        DrawCircle(circle_outer,thisLabel.getLayoutX()+MyController.GRID_WIDTH/2,
                thisLabel.getLayoutY()+MyController.GRID_HEIGHT/2,range,range<1500);
        if(thisLabel.getText().equals("Catapult"))
            DrawCircle(circle_inner,thisLabel.getLayoutX()+MyController.GRID_WIDTH/2,
                    thisLabel.getLayoutY()+MyController.GRID_HEIGHT/2, Catapult.DefaultInnerRange,true);

        anchorPane.getChildren().add(circle_inner);
        anchorPane.getChildren().add(circle_outer);
        anchorPane.getChildren().add(infoLabel);

        event.consume();
    }
}

class MouseExitDismissInfoHandler implements EventHandler<MouseEvent>{
    AnchorPane anchorPane;
    Label InfoLabel;
    Circle Inner;
    Circle Outer;

    MouseExitDismissInfoHandler(AnchorPane anchorPane,Circle Inner,Circle Outer,Label InfoLabel){
        this.anchorPane = anchorPane;
        this.InfoLabel = InfoLabel;
        this.Inner = Inner;
        this.Outer = Outer;
    }

    @Override
    public void handle(MouseEvent event) {
        anchorPane.getChildren().removeAll(Inner,Outer,InfoLabel);
//        anchorPane.getChildren().remove(anchorPane.getChildren().size()-1);
//        anchorPane.getChildren().remove(anchorPane.getChildren().size()-1);
//        anchorPane.getChildren().remove(anchorPane.getChildren().size()-1);


    }
}