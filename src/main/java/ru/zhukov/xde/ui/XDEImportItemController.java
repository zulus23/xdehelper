package ru.zhukov.xde.ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.zhukov.xde.domain.Enterprise;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gukov on 29.05.2017.
 */
public class XDEImportItemController extends AbstractController implements Initializable{

    @FXML
    private TableView itemView;
    @FXML
    private TableColumn columnItem;


    public  XDEImportItemController(Enterprise enterprise){
      super(enterprise);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
