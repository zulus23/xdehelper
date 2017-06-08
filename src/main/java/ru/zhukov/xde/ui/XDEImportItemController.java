package ru.zhukov.xde.ui;


import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import org.controlsfx.control.table.TableFilter;
import org.reactfx.EventStreams;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.util.ItemClipBoard;


import java.net.URL;
import java.time.Duration;
import java.util.*;

/**
 * Created by Gukov on 29.05.2017.
 */
public class XDEImportItemController extends AbstractController {

    @FXML
    private TableColumn columnItem;

    @FXML
    private TextArea wLog;

    public  XDEImportItemController(Enterprise enterprise){
      super(enterprise);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location,resources);

        wLog.setEditable(false);

        super.getItemView().getItems().addListener((ListChangeListener) c -> {
            if (super.getItemView().getItems().size()> 0 ) {
                wLog.setText(String.format(" К выгрузке %s изделий", String.valueOf(super.getItemView().getItems().size())));
            }else {
                wLog.setText("");
            }


        });
  }


    @Override
    protected void pasteItem(ActionEvent e){
        StringTokenizer tokenizer = new StringTokenizer(super.getClipboard().getString());
        super.getItemView().getItems().clear();
        Set<ItemClipBoard> itemClipBoards = new LinkedHashSet<>();
        while (tokenizer.hasMoreTokens()){
            itemClipBoards.add(new ItemClipBoard(tokenizer.nextToken()));

        }
        super.getItemView().getItems().addAll(itemClipBoards);
    }


}
