package coit12200.wis.roles;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;

public class SceneCoordinator {
    public enum SceneKey {LOGIN, PASSWORD, QUERY};

    private Stage stage;
    private HashMap<SceneKey, Scene> scenes = new HashMap<>();

    public SceneCoordinator(Stage stage){
        this.stage = stage;
    }

    public void addScene (SceneKey key, Scene value) {
        scenes.put(key, value);
    }

    public void setScene (SceneKey key){
        Scene s = scenes.get(key);

        stage.setResizable(false);
        stage.setTitle("Whiskey Information Systems");

        stage.setScene(s);
        stage.show();
    }

    public void start () {
        setScene(SceneKey.LOGIN);
    }
}
