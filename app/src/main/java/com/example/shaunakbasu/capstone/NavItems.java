package com.example.shaunakbasu.capstone;

/**
 * Created by shaunak basu on 15-11-2016.
 */
public class NavItems {

    String main_text;
    String sub_text;
    int image_id;

    public NavItems(String main_text,String sub_text,int image_id){
        this.main_text=main_text;
        this.sub_text=sub_text;
        this.image_id=image_id;
    }

    public String getMain_text() {
        return main_text;
    }

    public String getSub_text() {
        return sub_text;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }

    public void setSub_text(String sub_text) {
        this.sub_text = sub_text;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
