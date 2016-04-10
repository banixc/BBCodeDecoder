package com.npupt.npubits.bbcode;

public class BCUrl extends BCString {
    public String url;

    public BCUrl(String string,BCStyle style) {
        super(string,style);
        this.url = string;
    }

    public BCUrl(String string,String url,BCStyle style) {
        super(string,style);
        this.url = url;
    }

    @Override
    public String toString() {
        return "[url=" + url + "]" + string + "[/url]";
    }

    @Override
    public String toHtml() {
        return "<a href=\"" + url + "\">" + super.toHtml() + "</a>";
    }

    @Override
    public String toOmit() {
        return string;
    }

}
