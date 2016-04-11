package com.npupt.npubits.bbcode;

public class BCImg extends BCItem {

    protected String src;

    public BCImg(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "[img=" + src + "]";
    }
    @Override
    public String toHtml() {
        return "<img style=\"max-width:100%;height:auto\" src=\"" + src + "\"/>";
    }

    @Override
    public String toOmit() {
        return "[图片]" ;
    }

}
