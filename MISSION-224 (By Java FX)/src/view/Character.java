package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Character extends AnchorPane {

    private ImageView imageView;
    public SpriteAnimation animation;

    private int count = 5;
    private int columb = 5;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 415;
    private int height = 441;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        imageView.setScaleX(0.25);
        imageView.setScaleY(0.25);
        imageView.setSmooth(true);

        animation = new SpriteAnimation(imageView, Duration.millis(200), count, columb, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void moveX(int x) {
        boolean right = x > 0 ? true:false;
        for(int i=0; i<Math.abs(x); i++) {
            if(right) {
                this.setTranslateX(this.getTranslateX() + 1);
                imageView.setScaleX(0.25);
            }
            else {
                this.setTranslateX(this.getTranslateX() - 1);
                imageView.setScaleX(-0.25);
            }
        }
    }
}
