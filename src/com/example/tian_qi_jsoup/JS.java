package com.example.tian_qi_jsoup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class JS {
	/** 
	 * 根据jsoup方法获取htmlContent 
	        * 加入简单的时间记录 
	 * @throws IOException  
	 */  
	public String getContentByJsoup(String url){  
	    String content="";  
	    try {  
	        System.out.println("time=====start");  
	        Date startdate=new Date();  
	       /* File input = new File("C:\\Users\\Administrator\\Desktop\\-1.html"); 
	        doc = Jsoup.parse(input, "UTF-8", "");*/
	     Document doc=Jsoup.connect(url)  
	        .data("jquery", "java")  
	        .userAgent("Mozilla")  
	        .cookie("auth", "token")  
	        .timeout(50000)  
	        .get();  
	        Date enddate=new Date();  
	        Long time=enddate.getTime()-startdate.getTime();  
	        System.out.println("使用Jsoup耗时=="+time);  
	        System.out.println("time=====end");  
	        content=doc.toString();//获取iteye网站的源码html内容  
	        System.out.println(doc.title());//获取iteye网站的标题  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    // System.out.println(content);   
	    return content;  
	}  
	
    /** 
 * 使用jsoup来对文档分析 
        * 获取目标内容所在的目标层 
        * 这个目标层可以是div，table，tr等等 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws IOException 
 */  
public  String getDivContentByJsoup(String content) {  
	
	
    String divContent="";  
    Document doc=Jsoup.parse(content);  
    //Elements divs=doc.select("div.f1");
   
    Elements divs=doc.getElementsByClass("dn");  
   
    divContent=divs.toString();  
    String str=divs.text().toString();
    
   // System.out.println(divs.toString());
 /*   
    Document d=Jsoup.parse(divContent); 
    Elements div = d.getElementsByClass("wea");*/
    
    SimpleDateFormat formatBuilder = new SimpleDateFormat("dd");
	int d= Integer.parseInt(formatBuilder.format(new Date()));
	
	// System.out.println(""+ formatBuilder.format(new Date()));
	System.out.println(str.substring(str.indexOf(""+d+"日"),str.indexOf(""+(++d)+"日") ));
	System.out.println(str.substring(str.indexOf(""+d+"日"),str.indexOf(""+(++d)+"日") ));
	System.out.println(str.substring(str.indexOf(""+d+"日"),str.indexOf("今") ));
	
	System.out.println(str.substring(str.indexOf("今"),str.indexOf("明")));
	System.out.println(str.substring(str.indexOf("明"),str.indexOf("后")));
	System.out.println(str.substring(str.indexOf("后"), str.indexOf("风 周") )+"风");
	System.out.println(str.substring(str.indexOf("风 周") +2 ));
    // System.out.println("div==="+ str.substring());
	
	str=str.substring(str.indexOf(""+d+"日"),str.indexOf(""+(++d)+"日"))+"\n"
			+str.substring(str.indexOf(""+d+"日"),str.indexOf(""+(++d)+"日") )+"\n"
			+str.substring(str.indexOf(""+d+"日"),str.indexOf("今") )+"\n"
			+str.substring(str.indexOf("今"),str.indexOf("明"))+"\n"
			+str.substring(str.indexOf("明"),str.indexOf("后"))+"\n"
			+str.substring(str.indexOf("后"), str.indexOf("风 周") )+"风"+"\n"
			+str.substring(str.indexOf("风 周") +2 )+"\n"
			;
	
	
	d=0;
	System.out.println("123213"+str);
    return str;  
}  

/** 
 * 使用jsoup分析divContent 
 * 1.获取链接 2.获取url地址（绝对路径） 
 */  
public  void getLinksByJsoup(String divContent){  
    String abs="http://www.iteye.com/";  
    Document doc=Jsoup.parse(divContent,abs);  
    Elements linkStrs=doc.getElementsByTag("li");  
    System.out.println("链接==="+linkStrs.size());  
    for(Element linkStr:linkStrs){  
        String url=linkStr.getElementsByTag("a").attr("abs:href");  
        String title=linkStr.getElementsByTag("a").text();  
        System.out.println("标题:"+title+" url:"+url);  
    }  
}  

/** 
 * @method 测试获取内容程序 
 */  
/*public static void main(String[] args) throws IOException {  
      
    *//** 
     * 执行分析程序 
     *//*  
    String url="http://weather.com.cn/html/weather/101043600.shtml";  
    String HtmlContent=getContentByJsoup(url);  

  
    String divContent=getDivContentByJsoup(HtmlContent);  
   // getLinksByJsoup(divContent);  
}  */
}
