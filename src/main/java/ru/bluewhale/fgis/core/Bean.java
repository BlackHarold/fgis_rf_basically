package ru.bluewhale.fgis.core;

import java.util.List;

public abstract class Bean<T> {
    private String index;
    private String text;
    private List<T> list;

    public Bean() {
    }

    public Bean(String index, String text) {
        this.index = index;
        this.text = text;
    }

    public Bean(String index, String text, List<T> list) {
        this.index = index;
        this.text = text;
        this.list = list;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
