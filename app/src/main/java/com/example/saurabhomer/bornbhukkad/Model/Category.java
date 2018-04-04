package com.example.saurabhomer.bornbhukkad.Model;

/**
 * Created by saurabh omer on 28-Mar-18.
 */

public class Category {
     private String Name;
     private String Image;
     public Category()
     {

     }

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }
}
