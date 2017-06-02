package ru.zhukov.xde.db;

/**
 * Created by Zhukov on 31.05.2017.
 */
public enum DBConnect {

    GOTEK{
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_gotek;username=sa;password=kbghbrjy";
        }
    },
    Litar {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_litar;username=sa;password=kbghbrjy";
        }
    },
    Polypack {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_polypack;username=sa;password=kbghbrjy";
        }
    },
    Print {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_print;username=sa;password=kbghbrjy";
        }
    },
    CPU{
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_cpu;username=sa;password=kbghbrjy";
        }
    };




    public abstract String connectString();

}
