package com.example.androidtest5;

import android.graphics.Bitmap;

public class ImaginiDomeniu {
    private String text;
    private Bitmap imagine;
    private String link;

    public ImaginiDomeniu(String text, Bitmap imagine, String link) {
        this.text = text;
        this.imagine = imagine;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImaginiDomeniu{");
        sb.append("text='").append(text).append('\'');
        sb.append(", imagine=").append(imagine);
        sb.append(", link='").append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
