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
	 * ����jsoup������ȡhtmlContent 
	        * ����򵥵�ʱ���¼ 
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
	        System.out.println("ʹ��Jsoup��ʱ=="+time);  
	        System.out.println("time=====end");  
	        content=doc.toString();//��ȡiteye��վ��Դ��html����  
	        System.out.println(doc.title());//��ȡiteye��վ�ı���  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    // System.out.println(content);   
	    return content;  
	}  
	
    /** 
 * ʹ��jsoup�����ĵ����� 
        * ��ȡĿ���������ڵ�Ŀ��� 
        * ���Ŀ��������div��table��tr�ȵ� 
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
	System.out.println(str.substring(str.indexOf(""+d+"��"),str.indexOf(""+(++d)+"��") ));
	System.out.println(str.substring(str.indexOf(""+d+"��"),str.indexOf(""+(++d)+"��") ));
	System.out.println(str.substring(str.indexOf(""+d+"��"),str.indexOf("��") ));
	
	System.out.println(str.substring(str.indexOf("��"),str.indexOf("��")));
	System.out.println(str.substring(str.indexOf("��"),str.indexOf("��")));
	System.out.println(str.substring(str.indexOf("��"), str.indexOf("�� ��") )+"��");
	System.out.println(str.substring(str.indexOf("�� ��") +2 ));
    // System.out.println("div==="+ str.substring());
	
	str=str.substring(str.indexOf(""+d+"��"),str.indexOf(""+(++d)+"��"))+"\n"
			+str.substring(str.indexOf(""+d+"��"),str.indexOf(""+(++d)+"��") )+"\n"
			+str.substring(str.indexOf(""+d+"��"),str.indexOf("��") )+"\n"
			+str.substring(str.indexOf("��"),str.indexOf("��"))+"\n"
			+str.substring(str.indexOf("��"),str.indexOf("��"))+"\n"
			+str.substring(str.indexOf("��"), str.indexOf("�� ��") )+"��"+"\n"
			+str.substring(str.indexOf("�� ��") +2 )+"\n"
			;
	
	
	d=0;
	System.out.println("123213"+str);
    return str;  
}  

/** 
 * ʹ��jsoup����divContent 
 * 1.��ȡ���� 2.��ȡurl��ַ������·���� 
 */  
public  void getLinksByJsoup(String divContent){  
    String abs="http://www.iteye.com/";  
    Document doc=Jsoup.parse(divContent,abs);  
    Elements linkStrs=doc.getElementsByTag("li");  
    System.out.println("����==="+linkStrs.size());  
    for(Element linkStr:linkStrs){  
        String url=linkStr.getElementsByTag("a").attr("abs:href");  
        String title=linkStr.getElementsByTag("a").text();  
        System.out.println("����:"+title+" url:"+url);  
    }  
}  

/** 
 * @method ���Ի�ȡ���ݳ��� 
 */  
/*public static void main(String[] args) throws IOException {  
      
    *//** 
     * ִ�з������� 
     *//*  
    String url="http://weather.com.cn/html/weather/101043600.shtml";  
    String HtmlContent=getContentByJsoup(url);  

  
    String divContent=getDivContentByJsoup(HtmlContent);  
   // getLinksByJsoup(divContent);  
}  */
}
