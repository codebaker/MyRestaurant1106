package com.joan.and.elec007.myrestaurant1106;

public class Menu {
    private String menuSeq;
    private String menuName;
    private int menuCost;

    public Menu(String menuSeq, String menuName, int menuCost) {
        this.menuSeq = menuSeq;
        this.menuName = menuName;
        this.menuCost = menuCost;
    }

    public String getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(String menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(int menuCost) {
        this.menuCost = menuCost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Menu{");
        sb.append("menuSeq='").append(menuSeq).append('\'');
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuCost=").append(menuCost);
        sb.append('}');
        return sb.toString();
    }
}
