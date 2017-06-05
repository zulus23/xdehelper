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
public class XDEImportItemController extends AbstractController implements Initializable{

    @FXML
    private TableView itemView;
    @FXML
    private TableColumn columnItem;

    private ContextMenu contextMenu;

    private MenuItem insertMenuItem;
    private MenuItem clearMenuItem;
    private Clipboard clipboard;

    public  XDEImportItemController(Enterprise enterprise){
      super(enterprise);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clipboard = Clipboard.getSystemClipboard();
        contextMenu = new ContextMenu();
        insertMenuItem = new MenuItem("Вставить");
        EventStreams.ticks(Duration.ofMillis(200)).subscribe((tick -> {
            insertMenuItem.disableProperty().setValue(Objects.isNull(clipboard.getString()));
        }));

        insertMenuItem.setOnAction(this::pasteItem);
        clearMenuItem = new MenuItem("Очистить");
        clearMenuItem.disableProperty().bind(Bindings.isEmpty(itemView.getItems()));
        clearMenuItem.setOnAction(e -> {
            itemView.getItems().clear();
        });
        contextMenu.getItems().addAll(insertMenuItem, clearMenuItem);
        itemView.setContextMenu(contextMenu);
    }



    private void pasteItem(ActionEvent e){
        StringTokenizer tokenizer = new StringTokenizer(clipboard.getString());
        itemView.getItems().clear();
        while (tokenizer.hasMoreTokens()){

            itemView.getItems().add( new ItemClipBoard(tokenizer.nextToken()));
        }
    }


}
