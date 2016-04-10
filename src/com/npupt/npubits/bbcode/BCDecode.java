package com.npupt.npubits.bbcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BCDecode {
	
	/**
	 * 保存词法分析后的token列表
	 */
    protected List<BCToken> tokenList;
    /**
     * 保存字体大小的栈
     */
    protected LinkedList<Integer> sizeStack;
    /**
     * 保存字体颜色的栈
     */
    protected LinkedList<String> colorStack;
    /**
     * 字体的u、b、i属性栈
     */
    protected int uStack;
    protected int bStack;
    protected int iStack;
    
	/**
	 * @param string 传入一个BBCode的String来构造一个BCDecode对象
	 */
    public BCDecode(String string) {
        sizeStack = new LinkedList<>();
        colorStack = new LinkedList<>();
        tokenList = new ArrayList<>();
        
        //使用正则表达式来获取token
        Pattern p=Pattern.compile(StaticVal.regex,Pattern.CASE_INSENSITIVE);
        Matcher m=p.matcher(string);
          
        int lastEnd = 0;
        while(m.find()) {
            if(lastEnd != m.start())
                tokenList.add(new BCToken(string.substring(lastEnd,m.start()),StaticVal.STRING));

            String token = m.group();
            tokenList.add(new BCToken(token,getTokenType(token)));
            lastEnd=m.end();
        }
        if(lastEnd!=string.length())
        tokenList.add(new BCToken(string.substring(lastEnd,string.length()),StaticVal.STRING));
    }
    
    /**
     * 传入一个未知的String来判断其Token类型
     * @param token 一个未知的token String
     * @return Integer token 类型
     */
    protected static int getTokenType(String token) {
        switch (token) {
            case "[i]":
                return StaticVal.I_OPEN;
            case "[/i]":
                return StaticVal.I_CLOSE;
            case "[u]":
                return StaticVal.U_OPEN;
            case "[/u]":
                return StaticVal.U_CLOSE;
            case "[b]":
                return StaticVal.B_OPEN;
            case "[/b]":
                return StaticVal.B_CLOSE;
            case "[quote]":
                return StaticVal.QUOTE_OPEN;
            case "[/quote]":
                return StaticVal.QUOTE_CLOSE;
            case "[pre]":
                return StaticVal.PRE_OPEN;
            case "[/pre]":
                return StaticVal.PRE_CLOSE;
            case "[/size]":
                return StaticVal.SIZE_CLOSE;
            case "[/color]":
                return StaticVal.COLOR_CLOSE;
            case "[img]":
                return StaticVal.IMG_OPEN;
            case "[/img]":
                return StaticVal.IMG_CLOSE;
            case "[url]":
                return StaticVal.URL_OPEN;
            case "[/url]":
                return StaticVal.URL_CLOSE;
            case "[code]":
                return StaticVal.CODE_OPEN;
            case "[/code]":
                return StaticVal.CODE_CLOSE;
        }
        switch (token.substring(1,2)) {
            case "q":
                if(token.substring(7,8).equals("游"))
                    return StaticVal.QUOTE_OPEN_AID;
                else
                    return StaticVal.QUOTE_OPEN_ID;
            case "s":
                return StaticVal.SIZE_OPEN;
            case "c":
                return StaticVal.COLOR_OPEN;
            case "i":
                return StaticVal.IMG_OPEN_WITH_SRC;
            case "u":
                return StaticVal.URL_OPEN_WITH_URL;
            case "e":
                return StaticVal.EM;
        }
        if(token.matches(StaticVal.urlRegex))
            return StaticVal.URL;
        return StaticVal.UNKNOW;

    }

    public String getValue(int i) {
        return tokenList.get(i).value;
    }
    /**
     * 通过字体的各种属性栈获取当前字体的Style
     * @return 当前字体的Style
     */
    protected BCStyle getStyle() {
        int size = sizeStack.size()>0?sizeStack.getLast():0;
        String color = colorStack.size()>0?colorStack.getLast():null;
        return new BCStyle(size,color,uStack>0,iStack>0,bStack>0);
    }
    /**
     * 取得字体颜色
     * @param token 形如“[color=***]”的token
     * @return String 当前字体的color
     */
    public static String getColor(String token) {
        String color = token.substring(7,token.length()-1);
        return color;
    }
    
    /**
     * 取得字体大小
     * @param token 形如“[size=5]”的token
     * @return Integer 当前字体的size
     */
    public static Integer getSize(String token) {
        return Integer.parseInt(token.substring(6,7));
    }
    
    /**
     * 取得被引用者的ID
     * @param token 形如“[quote=***]”的token
     * @return String 被引用者的ID
     */
    public static String getQuoter(String token) {
            return token.length()>7?token.substring(7,token.length()-1):null;
    }
    
    public BCItem getItem() {
        return getItem(0,tokenList.size());
    }
    /**
     * 获取解析后的BBCode对象
     * @param start tokenList的起始位置
     * @param end tokenList的结束位置
     * @return 一个BCItem
     */
    public BCItem getItem(int start,int end) {
        if(start >= end)
            return new BCString("",null);
        int closeEnd;

        List<BCItem> list = new ArrayList<>();

        for(int i = start; i < end; i++) {
            switch (tokenList.get(i).type) {
            	//单Token直接转换为对象
                case StaticVal.URL:
                    list.add(new BCUrl(getValue(i),getStyle()));
                    continue;
                case StaticVal.EM:
                    list.add(new BCEmoji(getValue(i).substring(3,getValue(i).length()-1)));
                    continue;
                case StaticVal.IMG_OPEN_WITH_SRC:
                    list.add(new BCImg(getValue(i).substring(5,getValue(i).length()-1)));
                    continue;
                   
                //如果是字体属性则修改当前字体Style的属性
                case StaticVal.B_OPEN:
                    bStack ++;
                    continue;
                case StaticVal.U_OPEN:
                    uStack ++;
                    continue;
                case StaticVal.I_OPEN:
                    iStack ++;
                    continue;
                case StaticVal.B_CLOSE:
                    if(bStack>0) {
                        bStack--;
                        continue;
                    }
                case StaticVal.U_CLOSE:
                    if(uStack>0) {
                        uStack--;
                        continue;
                    }
                case StaticVal.I_CLOSE:
                    if(iStack>0) {
                        iStack--;
                        continue;
                    }
                case StaticVal.SIZE_OPEN:
                    sizeStack.push(getSize(getValue(i)));
                    continue;
                case StaticVal.SIZE_CLOSE:
                    if(sizeStack.size()>0) {
                        sizeStack.pop();
                        continue;
                    }
                case StaticVal.COLOR_OPEN:
                    colorStack.push(getColor(getValue(i)));
                    continue;
                case StaticVal.COLOR_CLOSE:
                    if(colorStack.size()>0) {
                        colorStack.pop();
                        continue;
                    }
                
                //对于需要寻找封闭标签的Token 在找到其对应的闭标签后递归调用getItem来获取标签里的BCItem
                case StaticVal.QUOTE_OPEN:
                case StaticVal.QUOTE_OPEN_AID:
                case StaticVal.QUOTE_OPEN_ID:
                    closeEnd = FindCloseTag(i+1,end,StaticVal.QUOTE_CLOSE,StaticVal.QUOTE_OPEN,StaticVal.QUOTE_OPEN_AID,StaticVal.QUOTE_OPEN_ID);
                    if(closeEnd > i) {
                        list.add(new BCQuote(getQuoter(getValue(i)),getItem(i+1,closeEnd)));
                        i = closeEnd;
                        continue;
                    }

                case StaticVal.URL_OPEN:
                    if(tokenList.size()> i+2 && tokenList.get(i+2).type == StaticVal.URL_CLOSE && tokenList.get(i+1).type == StaticVal.URL) {
                        list.add(new BCUrl(getValue(i + 1),getStyle()));
                        i += 2;
                        continue;
                    }

                case StaticVal.URL_OPEN_WITH_URL:
                    if(tokenList.size()> i+2 && tokenList.get(i+2).type == StaticVal.URL_CLOSE) {
                        list.add(new BCUrl(getValue(i + 1),getValue(i).substring(5,getValue(i).length()-1),getStyle()));
                        i += 2;
                        continue;
                    }

                case StaticVal.CODE_OPEN:
                    closeEnd = FindCloseTag(i+1,end,StaticVal.CODE_CLOSE);
                    if(closeEnd > i) {
                        list.add(new BCCode(tokenList,i+1,closeEnd));
                        i = closeEnd;
                        continue;
                    }


                case StaticVal.PRE_OPEN:
                    closeEnd = FindCloseTag(i+1,end,StaticVal.PRE_CLOSE);
                    if(closeEnd > i) {
                        list.add(new BCQuote(getItem(i+1,closeEnd),true));
                        i = closeEnd;
                        continue;
                    }
            }
            //以上情况均没有找到则将其当作String保存在列表中
            list.add(new BCString(getValue(i),getStyle()));
        }
        //若列表为空则返回一个空字符对象
        if(list.size() == 0)
            return new BCString("",null);
        //若为列表里为单值则只返回其第一个对象
        else if(list.size() == 1)
            return list.get(0);
        //否则返回一个由此列表构成的BCItems
        else
            return new BCItems(list);
    }

    /**
     * 用于寻找对应的闭标签
     * @param start tokenList的起始位置
     * @param end tokenList的结束位置
     * @param closeTag 寻找的闭标签
     * @param openTags 对应的开标签
     * @return Integer 若找到则返回tokenList的Index 否则返回未封闭的层数的负值
     */
    public int FindCloseTag(int start, int end, int closeTag, Integer... openTags){
    	//默认未封闭层数为-1
        int num = -1;

        for(int i = start; i < end; i++) {
            if(tokenList.get(i).type == closeTag) {
                num ++;
                if(num == 0) {
                	//已封闭则返回当前的Index
                    return i;
                }
            }
            for(Integer openTag:openTags) {
                if(tokenList.get(i).type == openTag)
                    num --;
            }
        }
        return num;
    }

}
