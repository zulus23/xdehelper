package ru.zhukov.xde.db;

/**
 * Created by Zhukov on 31.05.2017.
 */
public enum ConnectString {

    GOTEK{
        @Override
        public String getConnectString() {
            return "jdbc:sqlserver://SRV-SLDB\\SL_GOTEK:14333;databaseName=SL_GOTEK";
        }
    };





    public abstract String getConnectString();

}
