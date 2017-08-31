package ru.zhukov.xde.db;

/**
 * Created by Zhukov on 31.05.2017.
 */
public enum DBConnect {

    GOTEK("GOTEK"){
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_gotek;username=sa;password=kbghbrjy";
        }

        @Override
        public String persistenceName() {
             return  "serviceGotek";
        }
    },
    LITAR("Litar") {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_litar;username=sa;password=kbghbrjy";
        }
        @Override
        public String persistenceName() {
            return "serviceLitar";
        }
    },
    POLYPACK("Polypack") {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_polypack;username=sa;password=kbghbrjy";
        }
        @Override
        public String persistenceName() {
            throw new NoSuchMethodError("");
        }
    },
    PRINT("Print") {
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_print;username=sa;password=kbghbrjy";
        }
        @Override
        public String persistenceName() {
            throw new NoSuchMethodError("");
        }
    },
    CPU("CPU"){
        @Override
        public String connectString() {
            return "jdbc:sqlserver://SRV-SLDB:1433;databaseName=sl_cpu;username=sa;password=kbghbrjy";
        }
        @Override
        public String persistenceName() {
            throw new NoSuchMethodError("");
        }
    };

    private String nameDatabase;

     DBConnect(String nameDatabase){
        this.nameDatabase = nameDatabase;
    }

    public String getNameDatabase(){
        return  nameDatabase;
    }

    public abstract String connectString();
    public abstract String persistenceName();

}
