package ru.zhukov.xde.ui;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import org.reactfx.EventStreams;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.util.ItemClipBoard;


import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * Created by Gukov on 29.05.2017.
 */
public class XDEImportItemController extends AbstractController {

    @FXML
    private TableColumn columnItem;


    public  XDEImportItemController(Enterprise enterprise){
      super(enterprise);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
  }


    @Override
    protected void pasteItem(ActionEvent e){
        StringTokenizer tokenizer = new StringTokenizer(super.getClipboard().getString());
        super.getItemView().getItems().clear();
        while (tokenizer.hasMoreTokens()){

            super.getItemView().getItems().add( new ItemClipBoard(tokenizer.nextToken()));
        }
    }


}
