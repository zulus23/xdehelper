package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import ru.zhukov.xde.domain.Enterprise;

/**
 * Created by Gukov on 05.06.2017.
 */
public abstract class AbstractViewAction {
    protected TabPane tabPane;
    protected Enterprise selectedEnterprise;

    public AbstractViewAction(TabPane tabPane, Enterprise selectedEnterprise) {
        this.tabPane = tabPane;
        this.selectedEnterprise = selectedEnterprise;
    }

    public abstract void action(ActionEvent actionEvent);
}
