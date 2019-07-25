package view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameViewManager {

    private AnchorPane gamePane;
    private Stage gameStage;
    private Scene gameScene;

    private Stage mainStage;
    private AnimationTimer gameTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private ImageView protagonistRun = new ImageView("view/resources/characters/run.png");
    private Character player = new Character();

    private static final String GAME_BACKGROUND = "view/resources/game_background.jpg";
    private static final int GAME_WIDTH = 1100;
    private static final int GAME_HEIGHT = 700;

    public GameViewManager() {

        Initialize();
    }

    private void Initialize() {

        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();

        gameStage.setScene(gameScene);
    }

    private void MovementUpdate() {

        gameScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.RIGHT) {
                player.animation.play();
                player.animation.setOffsetX(415);
                player.moveX(10);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                player.animation.play();
                player.animation.setOffsetX(415);
                player.moveX(-10);
            }
            /*else if(e.getCode() == KeyCode.UP) {
                player.animation.play();
                player.animation.setOffsetY(441);
            } */
            else player.animation.stop();
        });
        gameScene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.RIGHT) {
                player.animation.stop();
            }
            else if(e.getCode() == KeyCode.LEFT) {
                player.animation.stop();
            }
        });
//        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if(event.getCode() == KeyCode.RIGHT) {
//                    player.animation.play();
//                    player.animation.setOffsetX(415);
//                    player.moveX(10);
//                }
//                else if(event.getCode() == KeyCode.LEFT) {
//                    player.animation.play();
//                    player.animation.setOffsetX(415);
//                    player.moveX(-10);
//                }
//                /*else if(event.getCode() == KeyCode.UP) {
//                    player.animation.play();
//                    player.animation.setOffsetY(441);
//                } */
//                else player.animation.stop();
//            }
//        });
//        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if(event.getCode() == KeyCode.RIGHT) {
//                    player.animation.stop();
//                }
//                else if(event.getCode() == KeyCode.LEFT) {
//                    player.animation.stop();
//                }
//            }
//        });
    }

    private void CreateCharacter() {

        player.setImageView(protagonistRun);
        player.getImageView().setLayoutX(-150);
        player.getImageView().setLayoutY(GAME_HEIGHT-500);
        gamePane.getChildren().addAll(player);
    }

    public void CreateNewGame(Stage mainStage) {

        this.mainStage = mainStage;
        mainStage.hide();
        CreateBackground();
        CreateCharacter();
        CreateGameLoop();
        gameStage.show();
    }

    private void CreateGameLoop() {

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
                MovementUpdate();
            }
        };

        gameTimer.start();
    }

    private void CreateBackground() {

        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for(int i=0; i<12; i++) {
            ImageView backgroundImage1 = new ImageView(GAME_BACKGROUND);
            ImageView backgroundImage2 = new ImageView(GAME_BACKGROUND);
            GridPane.setConstraints(backgroundImage1, i%3, i/3);
            GridPane.setConstraints(backgroundImage2, i%3, i/3);
            gridPane1.setLayoutX(-600);
            gridPane2.setLayoutX(-600);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground() {

        gridPane1.setLayoutX(gridPane1.getLayoutX() + 0.5);
        gridPane2.setLayoutX(gridPane2.getLayoutX() + 0.5);

        if(gridPane1.getLayoutX() >= -10) gridPane1.setLayoutX(-600);
        if(gridPane2.getLayoutX() >= -10) gridPane2.setLayoutX(-600);
    }
}
