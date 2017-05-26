package ru.zhukov.xde;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.zhukov.xde.ui.XDEApplicationController;

/**
 * Created by Gukov on 26.05.2017.
 */
public class BootstrapApplication extends Application{



    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BootstrapApplication.class.getResource("/ru/zhukov/xde/ui/XDEApplicationView.fxml"));
            XDEApplicationController controller = new XDEApplicationController();
            fxmlLoader.setController(controller);
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        }catch (Exception e ){
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
