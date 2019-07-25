package application;

import view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) {

        ViewManager manager = new ViewManager();
        manager.createMenuButtons();
        primaryStage = manager.getMainStage("Main Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
