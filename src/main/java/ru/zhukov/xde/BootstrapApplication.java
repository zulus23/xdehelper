package ru.zhukov.xde;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.zhukov.xde.ui.XDEApplicationController;
import ru.zhukov.xde.util.Databases;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

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
            stage.setTitle(String.format("Экспорт из SL"));
            stage.getIcons().add(new Image(getClass().getResource("/ru/zhukov/xde/assests/image/database-export.png").toExternalForm()));
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
