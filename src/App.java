//Conner Young
//10/28/25
//CSCI 3331-002
//
//Files: n/a
//
//Memory Matching Game
//A memory game where you have to match 8 pairs of tiles in a 4x4 grid
//by revealing them individually for a short window. A timer tracks
//time elapsed since the game began and stops when all tiles are matched.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
    /*
     * Sets the stage on which the game is played
     * @param primaryStage The stage on which the game is played
     * @return none
     */
    @Override
    public void start(Stage primaryStage) {
        GamePane pane = new GamePane();
        Scene scene = new Scene(pane, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /*
     * Main method launches application
     * @param args Arguments passed to the JVM
     * @return none
     */
    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
