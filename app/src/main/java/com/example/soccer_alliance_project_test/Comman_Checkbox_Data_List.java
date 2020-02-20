package com.example.soccer_alliance_project_test;

class Comman_Checkbox_Data_List {

    private int chaeck_item_id;
    private String check_item_name;
    private String check_item_image;


    public Comman_Checkbox_Data_List(int chaeck_item_id, String check_item_name) {
        this.chaeck_item_id = chaeck_item_id;
        this.check_item_name = check_item_name;
    }

    public Comman_Checkbox_Data_List(int chaeck_item_id, String check_item_name, String check_item_image) {
        this.chaeck_item_id = chaeck_item_id;
        this.check_item_name = check_item_name;
        this.check_item_image = check_item_image;
    }

    public int getChaeck_item_id() {
        return chaeck_item_id;
    }

    public String getCheck_item_name() {
        return check_item_name;
    }

    public String getCheck_item_image() {
        return check_item_image;
    }
}

