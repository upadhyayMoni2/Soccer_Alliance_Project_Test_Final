package com.example.soccer_alliance_project_test;

class Comman_Data_List {

    private String item_name;
    private String item_image;
    private int item_id;


    public Comman_Data_List(String item_name, String item_image, int item_id) {
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public int getItem_id() {
        return item_id;
    }
}

