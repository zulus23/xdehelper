package ru.zhukov.xde.ui;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import org.reactfx.EventStreams;
import ru.zhukov.xde.domain.Enterprise;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Gukov on 01.06.2017.
 */
public abstract class AbstractController implements Initializable {

    @FXML
    private TableView itemView;
    private Enterprise currentEnterprise;


    private ContextMenu contextMenu;

    private MenuItem insertMenuItem;
    private MenuItem clearMenuItem;
    private Clipboard clipboard;


    public  void setCurrentEnterprise(Enterprise enterprise){
        this.currentEnterprise = enterprise;
    }
    public  Enterprise getCurrentEnterprise(){
        return  currentEnterprise;
    }

    public AbstractController(Enterprise currentEnterprise) {
        this.currentEnterprise = currentEnterprise;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setup();
    }

    private  void setup(){
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

    };

    public Clipboard getClipboard() {
        return clipboard;
    }

    public TableView getItemView() {
        return itemView;
    }

    protected abstract void pasteItem(ActionEvent e);

}
