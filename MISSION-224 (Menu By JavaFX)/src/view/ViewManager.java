package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.GameButtons;
import model.GameSubScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewManager {

    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 900;
    private static final int MENU_BUTTON_START_X = 100;
    private static final int MENU_BUTTON_START_Y = 200;
    private static final String CREDITS_PATH = "src/model/documents/Credits.txt";
    private static final String SCORE_PATH = "src/model/documents/Score.txt";
    private static final String HELP_PATH = "src/model/documents/Help.txt";

    List<GameButtons> menuButtons;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameSubScene creditsSubScene;
    private GameSubScene helpSubScene;
    private GameSubScene scoreSubScene;
    private GameSubScene sceneToHide;

    public ViewManager() {

        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createBackground();
        createLogo();
        createSubScene();
    }

    public void showSubScene(GameSubScene subscene) {

        if(sceneToHide != null)
            sceneToHide.moveSubScene();
        subscene.moveSubScene();
        sceneToHide = subscene;
    }

    public void createSubScene() {

        creditsSubScene = new GameSubScene(600, 300);
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new GameSubScene(600, 300);
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new GameSubScene(600, 300);
        mainPane.getChildren().add(scoreSubScene);
    }

    public Stage getMainStage(String title) {
        mainStage.setTitle(title);
        return mainStage;
    }

    private void addButton(GameButtons button) {

        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public void createMenuButtons() {

        createStartButton();
        createMissonButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private String FileInputReader(String path) throws FileNotFoundException {

        String output;
        File file = new File(path);
        Scanner sc = new Scanner(file);

        sc.useDelimiter("\\Z");

        output = sc.next();
        sc.close();
        return output;
    }
    private void createStartButton() {

        GameButtons startButton = new GameButtons("Play");
        addButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.CreateNewGame(mainStage);
            }
        });
    }

    private void createMissonButton() {

        GameButtons missionsButton = new GameButtons("Missions");
        addButton(missionsButton);
    }

    private void createScoreButton() {

        GameButtons ScoreButton = new GameButtons("Score");
        addButton(ScoreButton);

        Label scoreLabel = null;
        try {
            scoreLabel = new Label(FileInputReader(SCORE_PATH));
        } catch (FileNotFoundException e) {
            System.out.println("'Credits.txt' file is not found and throwing exception");
        }

        assert scoreLabel != null;
        scoreLabel.setStyle("-fx-text-fill: darkblue;");
        scoreLabel.setLayoutX(180);
        scoreLabel.setLayoutY(60);
        scoreLabel.setScaleX(1.55);
        scoreLabel.setScaleY(1.55);
        scoreSubScene.getPane().getChildren().add(scoreLabel);

        ScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }

    private void createHelpButton() {

        GameButtons HelpButton = new GameButtons("Help");
        addButton(HelpButton);

        Label helpLabel = null;
        try {
            helpLabel = new Label(FileInputReader(HELP_PATH));
        } catch (FileNotFoundException e) {
            System.out.println("'Credits.txt' file is not found and throwing exception");
        }

        assert helpLabel != null;
        helpLabel.setStyle("-fx-text-fill: darkblue;");
        helpLabel.setLayoutX(180);
        helpLabel.setLayoutY(60);
        helpLabel.setScaleX(1.55);
        helpLabel.setScaleY(1.55);
        helpSubScene.getPane().getChildren().add(helpLabel);

        HelpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }

    private void createCreditsButton() {

        GameButtons CreditsButton = new GameButtons("Credits");
        addButton(CreditsButton);

        Label creditLabel = null;
        try {
            creditLabel = new Label(FileInputReader(CREDITS_PATH));
        } catch (FileNotFoundException e) {
            System.out.println("'Credits.txt' file is not found and throwing exception");
        }

        assert creditLabel != null;
        creditLabel.setStyle("-fx-text-fill: darkblue;");
        creditLabel.setLayoutX(180);
        creditLabel.setLayoutY(60);
        creditLabel.setScaleX(1.55);
        creditLabel.setScaleY(1.55);
        creditsSubScene.getPane().getChildren().add(creditLabel);

        CreditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);
            }
        });
    }

    private void createExitButton() {

        GameButtons ExitButton = new GameButtons("Exit");
        addButton(ExitButton);

        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    public void createBackground() {

        Image backgroundImage = new Image("view/resources/menu_background.jpg", SCREEN_WIDTH, SCREEN_HEIGHT, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {

        ImageView logo = new ImageView("view/resources/GameLogo.png");
        logo.setLayoutX(180);
        logo.setLayoutY(-160);
        logo.setSmooth(true);
        logo.setEffect(new DropShadow());

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }
}
