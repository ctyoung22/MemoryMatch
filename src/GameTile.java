import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GameTile extends Button {
    private String hiddenEmoji;
    public boolean isFlipped;
    public boolean isMatched;
    public static ArrayList<GameTile> flippedTiles = new ArrayList<GameTile>();

    /*
     * Constructor for GameTile
     * @param initialString String that displays when emoji is hidden
     * @param emoji Unicode string of hidden emoji on tile
     * @return none
     */
    public GameTile(String initialString, String emoji) {
        super(initialString);
        this.hiddenEmoji = emoji;
        this.isFlipped = false;
        this.isMatched = false;

        setPrefWidth(100);
        setPrefHeight(100);
        setFont(Font.font(40));
    }

    /*
     * Gets Unicode string value of this tile's emoji
     * @param none
     * @return hiddenEmoji The Unicode string value of this tile's emoji
     */
    public String getHiddenEmoji() {
        return this.hiddenEmoji;
    }

    /*
     * Helper method that defines the logic of tile behavior on click
     * @param none
     * @return none
     */
    private void toggleReveal() {
        if(this.isFlipped == false) {
            this.setText(this.hiddenEmoji);
            this.isFlipped = true;
            flippedTiles.add(this);
            setMouseTransparent(isFlipped);
        }
        else {
            this.setText("?");
            this.isFlipped = false;
            flippedTiles.remove(this);
            setMouseTransparent(isFlipped);
        }
    }

    /*
     * Reveals tile for 1 second on click, tile remains revealed if matched
     * @param none
     * @return none
     */
    public void triggerReveal() {
        this.toggleReveal();

        Timeline flipAnimation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if(!this.isMatched) {
                this.toggleReveal();
            }
        }));

        flipAnimation.setCycleCount(1);
        flipAnimation.play();
    }
}
