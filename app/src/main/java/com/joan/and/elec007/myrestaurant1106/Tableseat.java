package com.joan.and.elec007.myrestaurant1106;

public class Tableseat {
    private String tableseatSeq;
    private String tableseatName;

    public Tableseat(String tableseatSeq, String tableseatName) {
        this.tableseatSeq = tableseatSeq;
        this.tableseatName = tableseatName;
    }

    public String getTableseatSeq() {
        return tableseatSeq;
    }

    public void setTableseatSeq(String tableseatSeq) {
        this.tableseatSeq = tableseatSeq;
    }

    public String getTableseatName() {
        return tableseatName;
    }

    public void setTableseatName(String tableseatName) {
        this.tableseatName = tableseatName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tableseat{");
        sb.append("tableseatSeq='").append(tableseatSeq).append('\'');
        sb.append(", tableseatName='").append(tableseatName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
