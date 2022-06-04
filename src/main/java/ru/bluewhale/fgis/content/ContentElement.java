package ru.bluewhale.fgis.content;

public class ContentElement {
    private String link;
    private String text;

    public ContentElement(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
                "link='" + link + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
