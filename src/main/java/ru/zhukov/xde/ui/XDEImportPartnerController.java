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
import ru.zhukov.xde.util.PartnerClipBoard;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * Created by Gukov on 29.05.2017.
 */
public class XDEImportPartnerController extends AbstractController/* implements Initializable*/{

    @FXML
    private TableColumn columnPartner;
    @FXML
    private TableColumn columnLcr;
    @FXML
    private RadioButton rbCustomer;
    @FXML
    private RadioButton rbVendor;
    @FXML
    private RadioButton rbLcr;


    @FXML
    private CheckBox chWithLcr;

    public XDEImportPartnerController(Enterprise enterprise){
      super(enterprise);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location,resources);
        chWithLcr.disableProperty().bind(Bindings.selectBoolean(rbLcr.selectedProperty()));
        chWithLcr.disableProperty().addListener((e)->{
            if (chWithLcr.isDisable()){
                chWithLcr.setSelected(false);
            }
        });

    }


    @Override
    protected void pasteItem(ActionEvent e){
        StringTokenizer tokenizerPartnerLcr = new StringTokenizer(super.getClipboard().getString(),"\n");
        StringTokenizer tokenizer = new StringTokenizer(super.getClipboard().getString());

        super.getItemView().getItems().clear();
        if(chWithLcr.isSelected()){
            while (tokenizerPartnerLcr.hasMoreTokens()) {
                String partner = null;
                String lcr = null;
                String[] st = tokenizerPartnerLcr.nextToken().split("\\t+");
                if(st.length == 2){
                  partner = st[0].trim();
                  lcr = st[1].trim();
                } else {
                    partner = st[0].trim();
                }

               super.getItemView().getItems().add(new PartnerClipBoard(partner,lcr));
            }
        } else {
            while (tokenizer.hasMoreTokens()) {

                super.getItemView().getItems().add(new PartnerClipBoard(tokenizer.nextToken().trim()));
            }
        }
    }


}
