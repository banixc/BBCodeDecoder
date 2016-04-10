package com.npupt.npubits.bbcode;

public class BCStyle {
    public int size;
    public String color;
    public boolean u;
    public boolean i;
    public boolean b;
    /**
     * 
     * @param size 文本大小
     * @param color 文本颜色
     * @param u 是否有下划线
     * @param i 是否斜体
     * @param b 是否加粗
     */
    public BCStyle(int size,String color,boolean u, boolean i,boolean b){
        this.size = size;
        this.color = color;
        this.u = u ;
        this.i = i ;
        this.b = b;
    }
}


