package com.oyp.recyclerview.itemtouchhelper.view;

public class SliderItemDomain {
    int icon=0;
    String name="";
    String label_name="";
    String icon_selected="";
    String icon_unselected="";
    String click_action="";



    public SliderItemDomain(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public SliderItemDomain(String label_name, String icon_selected, String icon_unselected, String click_action) {
        this.label_name = label_name;
        this.icon_selected = icon_selected;
        this.icon_unselected = icon_unselected;
        this.click_action = click_action;
    }


    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getIcon_selected() {
        return icon_selected;
    }

    public void setIcon_selected(String icon_selected) {
        this.icon_selected = icon_selected;
    }

    public String getIcon_unselected() {
        return icon_unselected;
    }

    public void setIcon_unselected(String icon_unselected) {
        this.icon_unselected = icon_unselected;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
