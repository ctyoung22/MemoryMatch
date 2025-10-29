import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class GamePane extends BorderPane {
    GridPane gridPane;
    HBox timePane;
    int tileAssignCount, numMatches;
    static ArrayList<String> emojis = new ArrayList<String>(Arrays.asList("🐓", "🐓", "🐬", "🐬", "🐒", "🐒", "🐘", "🐘", "🐉", "🐉", "🐈", "🐈", "🐊", "🐊", "🐟", "🐟"));
    Timeline timerAnimation;

    /*
     * Constructor for GamePane
     * @param none
     * @return none
     */
    public GamePane() {
        gridPane = new GridPane();
        timePane = new HBox();
        tileAssignCount = 0;
        numMatches = 0;
        Collections.shuffle(emojis);

        setupGridPane();
        setupTimePane();
    }

    /*
     * Sets up the grid of tiles and defines tile behavior when clicked
     * @param none
     * @return none
     */
    public void setupGridPane() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                GameTile btn = new GameTile("?", emojis.get(tileAssignCount));
                
                btn.setOnAction( e -> {
                    if(!checkForMatch(btn)) {
                        btn.triggerReveal();
                    }
                    checkGameEnd();
                });

                gridPane.add(btn, j, i);

                tileAssignCount++;
            }
        }
        gridPane.setAlignment(Pos.CENTER);

        setCenter(gridPane);
    }

    /*
     * Sets up pane with timer and defines timer behavior
     * @param none
     * @return none
     */
    public void setupTimePane() {
        Label label = new Label("Time elapsed: 0 secs");
        timePane.getChildren().add(label);
        timePane.setAlignment(Pos.BOTTOM_CENTER);

        SimpleIntegerProperty count = new SimpleIntegerProperty();
        count.addListener( (ov) -> {
            label.setText("Time elapsed: " + count.getValue() + " secs");
        });

        timerAnimation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            count.setValue(count.getValue() + 1);
        }));

        setBottom(timePane);

        timerAnimation.setCycleCount(Timeline.INDEFINITE);
        timerAnimation.play();
    }

    /*
     * Checks among currently revealed tiles for a match with most recently clicked tile,
     * and disables matched tiles when discovered
     * @param tile Most recently clicked tile
     * @return true/false If match was found or not
     */
    public boolean checkForMatch(GameTile tile) {
        for(GameTile otherTile : GameTile.flippedTiles) {
            if(otherTile.getHiddenEmoji() == tile.getHiddenEmoji()) {
                tile.isMatched = true;
                tile.triggerReveal();
                tile.setDisable(true);
                otherTile.isMatched = true;
                otherTile.setDisable(true);

                numMatches++;

                return true;
            }
        }
        return false;
    }

    /*
     * Checks whether all tiles have been matched, stops timer if true
     * @param none
     * @return none
     */
    public void checkGameEnd() {
        if(numMatches >= 8) {
            timerAnimation.pause();
        }
    }
}
