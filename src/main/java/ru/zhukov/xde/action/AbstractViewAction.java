package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.ui.AbstractController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gukov on 05.06.2017.
 */
public abstract class AbstractViewAction {
    protected TabPane tabPane;
    protected Enterprise selectedEnterprise;
    protected Map<Tab,AbstractController> mapController;

    public AbstractViewAction(TabPane tabPane, Enterprise selectedEnterprise, Map<Tab,AbstractController> hashMapController) {
        this.tabPane = tabPane;
        this.selectedEnterprise = selectedEnterprise;
        this.mapController = hashMapController;
    }

    public abstract void action(ActionEvent actionEvent);




}
