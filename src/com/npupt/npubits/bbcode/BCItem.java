package com.npupt.npubits.bbcode;

public abstract class BCItem {

    public abstract String toString();
	/**
	 * 转换为html以便在WebView中显示
	 * @return String 对应的html
	 */
    public abstract String toHtml();
    /**
     * 获取其简略信息以便在TextView中显示
     * @return String 简略信息
     */
    public abstract String toOmit();
}
