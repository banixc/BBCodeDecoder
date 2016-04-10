package com.npupt.npubits.bbcode;

public class BCString extends BCItem {

    protected String string;
    protected BCStyle style;

    public BCString(String string,BCStyle style) {
        this.string = string;
        this.style = style;
    }

    @Override
    public String toString() {
        return string;
    }
    @Override
    public String toHtml() {
        String html = string;
        if(style !=null) {
            html = style.u ? "<u>" + html + "</u>" : html;
            html = style.i ? "<i>" + html + "</i>" : html;
            html = style.b ? "<b>" + html + "</b>" : html;
            html = style.color != null ? "<span style=\"color: " + style.color + ";\">" + html + "</span>" : html;
            html = style.size > 0 ? "<font size=\"" + style.size + "\">" + html + "</font>" : html;
        }
        return html.replaceAll("\\n","<br/>");
    }

    @Override
    public String toOmit() {
        return toString().replace('\n',' ');
    }

}
