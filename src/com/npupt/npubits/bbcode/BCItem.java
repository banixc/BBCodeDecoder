package com.npupt.npubits.bbcode;

public abstract class BCItem {

    public abstract String toString();
	/**
	 * ת��Ϊhtml�Ա���WebView����ʾ
	 * @return String ��Ӧ��html
	 */
    public abstract String toHtml();
    /**
     * ��ȡ�������Ϣ�Ա���TextView����ʾ
     * @return String ������Ϣ
     */
    public abstract String toOmit();
}
