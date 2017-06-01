package ru.zhukov.xde.ui;

import ru.zhukov.xde.domain.Enterprise;

/**
 * Created by Gukov on 01.06.2017.
 */
public abstract class AbstractController {

    private Enterprise currentEnterprise;

    public  void setCurrentEnterprise(Enterprise enterprise){
        this.currentEnterprise = enterprise;
    }
    public  Enterprise getCurrentEnterprise(){
        return  currentEnterprise;
    }

    public AbstractController(Enterprise currentEnterprise) {
        this.currentEnterprise = currentEnterprise;
    }
}
