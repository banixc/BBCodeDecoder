# BBCodeDecoder

## 背景
过去BBS、论坛、Blog也曾经设计开放HTML语法让用户使用，但却衍生出语法输入错误时造成整页排版错乱、有心人利用HTML语法进行XSS式的攻击与破坏等问题。BBCode通过特定的[标记]进行语法过滤后转换为HTML标签能有效阻止各种可能发生的恶意破坏。BBCode目前广泛用于基于php开发的论坛，而基于其他语言的开源库凤毛麟角。
为方便学校某资源站的Android客户端的开发，让用户在网页端和移动端获取统一的体验，同时结合《编译原理》课程的一部分知识，写出了这个Java的BBCode的解析器。

## 使用方法

	BCDecode bCDecode = new BCDecode(String bbcodeContext);
	BCItem item = bCDecode.getItem();
	String html = item.toHtml();

或直接使用已编译的`BBCodeDecoder.jar`:
`java -jar BBCodeDecoder.jar 1.txt 2.txt 3.txt `
来生成对应的HTML

## 支持的标签

`[i]斜体[/i]` 
`[b]粗体[/b]`
`[u]下划线[/u]`
`[size=1-7]字体大小[/size]`
`[color=blue]字体颜色[/size]`
`[url=***]带文本的链接[/url]`
`[url]链接[/url]` (可省略)
`[img]图片链接[/img]`
`[img=图片链接]`
`[quote=***]引用内容[/quote]`
`[code]代码[/code]`
`[pre]预格式化文本[/code]`

## 主要类介绍

### Item基类 BCItem(abstract)

声明了 toString(),toHtml(),toOmit()三个抽象函数以便实现多态
#### toHtml()
输出为HTML
#### toOmit()
输出简略的String

### BCEmoji extends BCItem

保存一个Emoji表情 [em**]

### BCImg extends BCItem

保存一个图片 [img=***]

### BCCode extends BCItem

保存一段代码片段 [code]***[/code]

### BCString extends BCItem

保存一段文本 
String string:文本的值
BCStyle style:文本的风格

### BCUrl extends BCString

保存一段带链接的文本 
String url:其指向的链接

### BCItems extends BCItem

保存一个BCItem的列表

### BCQuote extends BCItem

保存一个引用
String quoter:被引用者
BCItem bCItem:被引用的内容

### StaticVal

保存静态变量
* Token类型
* regex 正则表达式

### 核心类 BCDecode

#### 构造函数 BCDecode(String)
传入BBCode的初始String，通过正则表达式分割Token，使用getTokenType()获取Token的类型后生成一个TokenList。
#### 解析函数 getItem(int start,int end)
遍历从start到end范围的Token以生成一个BCItem并返回
