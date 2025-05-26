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
 * JavaFX App
 */
public class App extends Application {

    /**
     *
     * @param stage
     * @throws IOException
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
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     *
     * @param key
     * @return
     * @throws Exception
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