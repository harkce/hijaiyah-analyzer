package com.dblab.hijaiyahanalyzer.model;

import java.io.Serializable;

/**
 * Created by dblab on 01/09/16.
 */
public class Hijaiyah implements Serializable {

    private int drawableId;
    private String name;

    public Hijaiyah(int drawableId, String name) {
        this.drawableId = drawableId;
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
