package com.npupt.npubits.bbcode;

import java.util.List;

public class BCCode extends BCItem{
    protected String code;

    public BCCode(String code) {
        this.code = code;
    }

    public BCCode(List<BCToken> list,int start,int end) {
        code = "";
        for(int i = start; i < end; i++) {
            code += list.get(i).toString();
        }
    }

    @Override
    public String toString() {
        return "[code]" + code + "[/code]";
    }

    @Override
    public String toHtml() {
        return "<div class=\"code\">" + code + "</div>";
    }

    @Override
    public String toOmit() {
        return "[代码]" + code.replace('\n',' ');
    }


}
