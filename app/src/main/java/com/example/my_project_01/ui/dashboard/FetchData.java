package com.example.my_project_01.ui.dashboard;

import java.io.Serializable;

public class FetchData implements Serializable {
    String image, title, det, det2, price;

    public FetchData() {

    }

    public FetchData(String image, String title, String det, String det2, String price) {
        this.image = image;
        this.title = title;
        this.det = det;
        this.det2 = det2;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDet() {
        return det;
    }

    public void setDet(String det) {
        this.det = det;
    }

    public String getDet2() {
        return det2;
    }

    public void setDet2(String det2) {
        this.det2 = det2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
