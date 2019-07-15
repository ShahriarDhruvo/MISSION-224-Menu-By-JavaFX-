package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameViewManager {

    private AnchorPane gamePane;
    private Stage gameStage;
    private Scene gameScene;

    private Stage mainStage;
    private ImageView protagonist;
    private AnimationTimer gameTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;

    private static final String GAME_BACKGROUND = "view/resources/game_background.jpg";
    private static final int GAME_WIDTH = 1100;
    private static final int GAME_HEIGHT = 700;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;

    public GameViewManager() {

        Initialize();
        createKeyListers();
    }

    private void createKeyListers() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                }
                else if(event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                }
                else if(event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                }
            }
        });
    }

    private void Initialize() {

        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();

        gameStage.setScene(gameScene);
    }

    private void CreateCharacter() {
        protagonist = new Protagonist().getImage();
        gamePane.getChildren().addAll(protagonist);
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
                //moveBackground();
                AllCharacterMovements();
            }
        };

        gameTimer.start();
    }

    private void AllCharacterMovements() {
        CharacterMovements(protagonist);
    }

    private void CharacterMovements(ImageView character) {

        if(isLeftKeyPressed && !isRightKeyPressed) {
            if(character.getLayoutX() > -100) {
                character.setLayoutX(character.getLayoutX() - 3);
            }
        }

        if(!isLeftKeyPressed && isRightKeyPressed) {
            character.setLayoutX(character.getLayoutX() + 3);
        }
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
