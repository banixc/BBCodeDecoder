package com.npupt.npubits.bbcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by root on 2016/4/7.
 */
public class BCItems extends BCItem implements Iterable<BCItem>{

    protected List<BCItem> itemList;

    public BCItems() {
        itemList = new ArrayList<>();
    }

    public BCItems(BCItem item) {
        this();
        itemList.add(item);
    }

    public BCItems(List<BCItem> items) {
        itemList = items;
    }

    @Override
    public String toString() {
        String string = "";
        for(BCItem item:itemList) {
            string +=item;
        }
        return string;
    }

    @Override
    public Iterator<BCItem> iterator() {
        return itemList.iterator();
    }

    @Override
    public String toHtml() {
        String string = "";
        for(BCItem item:itemList) {
            string +=item.toHtml();
        }
        return string;
    }

    @Override
    public String toOmit() {
        String string = "";
        if(itemList.size()<1)
            return string;
        for(BCItem item:itemList) {
            if(!(item instanceof BCQuote))
                string += item.toOmit() + " ";
            else
                string += "[引用] ";
        }
        return string;
    }
}
