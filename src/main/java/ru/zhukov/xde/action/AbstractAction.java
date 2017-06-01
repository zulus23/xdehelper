package ru.zhukov.xde.action;

import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import ru.zhukov.xde.domain.Enterprise;

/**
 * Created by Gukov on 01.06.2017.
 */
public class AbstractAction {
    protected final Enterprise enterprise;
    protected Tab control;

    public AbstractAction(Tab control, Enterprise enterprise) {
        this.control = control;
        this.enterprise = enterprise;
    }
}
