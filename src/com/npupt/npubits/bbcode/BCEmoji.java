package com.npupt.npubits.bbcode;


public class BCEmoji extends BCItem {

    protected int emojiId;

    public BCEmoji(int emojiId) {
        this.emojiId = emojiId;
    }

    public BCEmoji(String emojiId) {
        this(Integer.parseInt(emojiId));
    }

    @Override
    public String toString() {
        return "[em" + emojiId + "]";
    }
    @Override
    public String toHtml() {
        return "<img src=\"file:///android_asset/em" + emojiId + ".gif\" alt=\"[em13]\">";
    }

    @Override
    public String toOmit() {
        return toString();
    }

}
