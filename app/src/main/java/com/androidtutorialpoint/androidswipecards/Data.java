package com.androidtutorialpoint.androidswipecards;

/**
 * Created by navneet on 20/11/16.
 */

public class Data {

    private String description;

    private int number;

    public Data(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

}
