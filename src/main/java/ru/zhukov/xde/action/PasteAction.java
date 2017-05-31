package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import ru.zhukov.xde.domain.Item;
import ru.zhukov.xde.util.FXUtils;
import ru.zhukov.xde.util.ItemClipBoard;

import java.util.StringTokenizer;


/**
 * Created by Gukov on 30.05.2017.
 */
public class PasteAction {

   private Tab control;

    public PasteAction(Tab control) {
        this.control = control;

    }


    public void action(ActionEvent e) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if( control.getText().equals("Экспорт изделий")){

         TableView tableView =  FXUtils.getChildByID(control.getTabPane(),"itemView");
            StringTokenizer tokenizer = new StringTokenizer(clipboard.getString());
            tableView.getItems().clear();
            while (tokenizer.hasMoreTokens()){

               tableView.getItems().add( new ItemClipBoard(tokenizer.nextToken()));
            }


        }

    }

}
