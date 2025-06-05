package coit12200.wis.roles;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * SceneCoordinator is responsible for managing different scenes in the application.
 * It allows adding scenes with a key and switching between them.
 * @author Jacob Duckworth
 */
public class SceneCoordinator {
    /**
     * SceneKey is an enum representing the different scenes in the application.
     * LOGIN: The login scene.
     * PASSWORD: The password scene.
     * QUERY: The query scene.
     */
    public enum SceneKey {LOGIN, PASSWORD, QUERY};

    private Stage stage;
    private HashMap<SceneKey, Scene> scenes = new HashMap<>();

    /**
     * Constructor for SceneCoordinator.
     * @param stage the primary stage of the application
     */
    public SceneCoordinator(Stage stage){
        this.stage = stage;
    }

    /**
     * Adds a scene to the coordinator with a specific key.
     * @param key the key to identify the scene
     * @param value the scene to be added
     */
    public void addScene (SceneKey key, Scene value) {
        scenes.put(key, value);
    }

    /**
     * Sets the current scene based on the provided key.
     * It also sets the stage properties such as resizable and title.
     * @param key the key of the scene to be set
     */
    public void setScene (SceneKey key){
        Scene s = scenes.get(key);

        stage.setResizable(false);
        stage.setTitle("Whiskey Information Systems");

        stage.setScene(s);
        stage.show();
    }

    /**
     * Starts the application by setting the initial scene to LOGIN.
     */
    public void start () {
        setScene(SceneKey.LOGIN);
    }
}
