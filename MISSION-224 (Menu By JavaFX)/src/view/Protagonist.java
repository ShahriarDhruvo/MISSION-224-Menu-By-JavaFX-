package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Protagonist extends AnchorPane {

    private static final String PATH = "view/resources/characters/idle2.png";
    private ImageView image;
    private SpriteAnimation animation;

    int count = 3;
    int columb = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 32;
    int height = 50;

    public Protagonist() {
        image = new ImageView(PATH);
        image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        //image.setLayoutX(100);
        //image.setLayoutY(200);
        //image.setSmooth(true);
        //image.setScaleX(0.35);
        //image.setScaleY(0.35);

        animation = new SpriteAnimation(image, Duration.millis(200), count, columb, offsetX, offsetY, width, height);
        getChildren().addAll(image);
    }

    public ImageView getImage() {
        return image;
    }
}
