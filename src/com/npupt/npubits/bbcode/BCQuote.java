package com.npupt.npubits.bbcode;

public class BCQuote extends BCItem {
    protected String quoter;
    protected BCItem bCItem;
    protected boolean pre;

    public BCQuote(String quoter,BCItem bCItem) {
        this.quoter = quoter;
        this.bCItem = bCItem;
        this.pre = false;
    }

    public BCQuote(BCItem bCItem,boolean pre) {
        this.quoter = null;
        this.bCItem = bCItem;
        this.pre = true;
    }

    @Override
    public String toString() {
        return pre?"[pre]" + bCItem + "[/pre]":
                quoter==null?"[quote]"+bCItem+"[/quote]":"[quote=" + quoter + "]" +bCItem +"[/quote]";
    }
    @Override
    public String toHtml() {
        return pre?"<pre>" + bCItem.toHtml() + "</pre>":"<div style=\"margin:10;padding:10;padding-right:0;margin-right:10;border-left-style:solid;border-color:#C8C8C8\">" + ((quoter!=null)?"<span style=\"color:blue\">@" + quoter + "</span><br/>" :"") + bCItem.toHtml() + "</div>";
    }

    @Override
    public String toOmit() {
        return bCItem.toOmit();
    }

}
