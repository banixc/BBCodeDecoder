package com.npupt.npubits.bbcode;

public class BCStyle {
    public int size;
    public String color;
    public boolean u;
    public boolean i;
    public boolean b;
    /**
     * 
     * @param size �ı���С
     * @param color �ı���ɫ
     * @param u �Ƿ����»���
     * @param i �Ƿ�б��
     * @param b �Ƿ�Ӵ�
     */
    public BCStyle(int size,String color,boolean u, boolean i,boolean b){
        this.size = size;
        this.color = color;
        this.u = u ;
        this.i = i ;
        this.b = b;
    }
}


