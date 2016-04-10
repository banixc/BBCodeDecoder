package com.npupt.npubits.bbcode;

import java.util.HashMap;
import java.util.Map;

public class StaticVal {

    public static final int UNKNOWN = -1;
    public static final int STRING = 0;
    public static final int QUOTE_OPEN = 1;
    public static final int QUOTE_OPEN_ID = 2;
    public static final int QUOTE_OPEN_AID = 3;
    public static final int SIZE_OPEN = 4;
    public static final int COLOR_OPEN = 5;
    public static final int U_OPEN = 6;
    public static final int I_OPEN = 7;
    public static final int B_OPEN = 8;
    public static final int IMG = 9;
    public static final int IMG_OPEN_WITH_SRC = 10;
    public static final int URL_OPEN = 11;
    public static final int URL_OPEN_WITH_URL = 12;
    public static final int EM = 13;
    public static final int PRE_OPEN = 15;
    public static final int CODE_OPEN = 16;
    public static final int URL = 17;

    public static final int QUOTE_CLOSE = 50;
    public static final int SIZE_CLOSE = 51;
    public static final int COLOR_CLOSE = 52;
    public static final int U_CLOSE = 53;
    public static final int I_CLOSE = 54;
    public static final int B_CLOSE = 55;
    public static final int URL_CLOSE = 57;
    public static final int PRE_CLOSE = 58;
    public static final int CODE_CLOSE = 59;

    public static String regex;

    public static final String urlRegex = "[a-zA-z]+://[^\\s]*";
    public static final String imgRegex = "\\[img\\][a-zA-z]+://[^\\s]*?(\\.jpg|\\.gif|\\.png|\\.jpeg)\\[/img\\]";

    public static final String[] tokenList = new String[]{
            "b",
            "/b",
            "i",
            "/i",
            "u",
            "/u",
            "img\\][a-zA-z]+://[^\\s]*?(\\.jpg|\\.gif|\\.png|\\.jpeg)\\[/img",
            "img=[a-zA-z]+://[^\\s]*?(\\.jpg|\\.gif|\\.png|\\.jpeg)",
            "url",
            "/url",
            "url=[a-zA-z]+://[^\\s+^\\[]*",
            "size=[1234567]",
            "/size",
            "color=[a-zA-Z0-9,\\#]{3,20}",
            "/color",
            "quote",
            "quote=[A-Za-z0-9\\u4e00-\\u9fa5,\\.,\\*,\\:]{1,40}",
            "/quote",
            "pre",
            "/pre",
            "em[0-9]{1,3}",
            "code",
            "/code"
    };

    static
    {
        regex = getRegex();
    }

    private static String getRegex() {
        String regex = "";
        for(int i = 0; i < tokenList.length;i ++) {
            regex += "\\[" + tokenList[i] + "\\]|";
        }
        return regex + "[a-zA-z]+://[^\\s+^\\[]*";
    }

}
