package com.brim.Pojo;

import org.json.JSONObject;

/**
 * Created by apple on 17/08/17.
 */

public class TransactionListData {
    int Image;
    String Name;
    String Type;
    String Price;
    String Status;

    JSONObject ItemObject;

    public JSONObject getItemObject() {
        return ItemObject;
    }

    public void setItemObject(JSONObject itemObject) {
        ItemObject = itemObject;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
