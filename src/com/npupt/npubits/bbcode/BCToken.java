package com.npupt.npubits.bbcode;

public class BCToken{
    public String value;
    public int type;
    public BCToken(String value,int type){
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return value;
    }
}
