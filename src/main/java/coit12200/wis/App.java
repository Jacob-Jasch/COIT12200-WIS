package coit12200.wis;

import coit12200.wis.data.UserData;
import coit12200.wis.data.WhiskeyData;
import coit12200.wis.roles.*;
import coit12200.wis.roles.SceneCoordinator.SceneKey;
import coit12200.wis.view.LoginController;
import coit12200.wis.view.PasswordController;
import coit12200.wis.view.QueryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main application class for the Whiskey Information System (WIS).
 * It initializes the application, and sets up the scene coordinator 
 * This class is responsible for creating the initial scenes and injecting
 * necessary data managers and validators into the controllers.
 * 
 * @author Jacob Duckworth
 */
public class App extends Application {

    /**
     * Starts the JavaFX application by initializing the scene coordinator
     * and creating the initial scenes for the application.
     * @param stage the primary stage for this application, onto which the application scene can be set
     * @throws IOException if an I/O error occurs while loading the FXML files
     */
    @Override
    public void start(Stage stage) throws IOException {
        SceneCoordinator sc = new SceneCoordinator(stage);
        // Configure each scene and add it to the coordinator.
        // Transitions between scenes are achieved by the controller for a
        // scene requesting the coordinator to make a particular scene the
        // root node of the scene graph.
        try {
            // create data related objects
            WhiskeyData wd = new WhiskeyData();
            WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
            WhiskeyDataValidator wdv = new WhiskeyDataValidator();
            UserData ud = new UserData();
            UserDataManager udm = new UserDataManager(ud);
            UserDataValidator udv = new UserDataValidator();
            // connect to the database
            wd.connect();
            ud.connect();
            // create the query scene
            Scene qs = makeScene(SceneKey.QUERY);
            Scene ls = makeScene(SceneKey.LOGIN);
            Scene ps = makeScene(SceneKey.PASSWORD);
            // inject required objects into the query controller
            QueryController qc = (QueryController) qs.getUserData();
            LoginController lc = (LoginController) ls.getUserData();
            PasswordController pc = (PasswordController) ps.getUserData();

            qc.inject(sc, wdm, wdv);
            lc.inject(sc,udm, udv);
            pc.inject(sc,udm, udv);
            sc.addScene(SceneKey.QUERY, qs);
            sc.addScene(SceneKey.LOGIN, ls);
            sc.addScene(SceneKey.PASSWORD, ps);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        sc.start();
    }

    /**
     * The main method to launch the JavaFX application.
     * This method is the entry point of the application and is called by the JavaFX runtime.
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Creates a Scene object based on the provided SceneKey.
     * It loads the corresponding FXML file and sets the controller for the scene.
     * @param key the SceneKey that determines which scene to create
     * @return a Scene object initialized with the specified FXML file and its controller
     * @throws Exception if an error occurs while loading the FXML file or creating the scene
     */
    private static Scene makeScene(SceneKey key) throws Exception  {
        // construct path name for fxml file
        String fxml = "/coit12200/wis/view/"+key.name().toLowerCase()+".fxml";
        // create scene object and add a reference to its controller object
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        scene.setUserData(loader.getController());
        return scene;
    }
}