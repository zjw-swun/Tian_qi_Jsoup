package com.example.tian_qi_jsoup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText  e_city;
	Button b;
	String cityname;
	//Handler handler=null;
	TextView t;
	ProgressDialog 	progressDialog;
	WebView wv;
	String showmsg="tag";
	JS js=new JS();
	String citycode="";
	 String url="http://weather.com.cn/html/weather/"+citycode+".shtml";  
	 String HtmlContent=null;
	 String tianqi=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e_city=  (EditText) findViewById(R.id.editText1);
		t=(TextView) findViewById(R.id.textView2);
		wv=(WebView) findViewById(R.id.webView1);
        b =(Button)findViewById(R.id.button1);   
        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cityname=e_city.getText().toString().trim();
				System.out.println(cityname);
				new  Thread(new Runnable() {
					
					@Override
					public void run() {
						try{
							//new InputSource(MainActivity.class.getClassLoader().getResourceAsStream("NewFile.xml"))
							//存储天气预报 
				        	SAXBuilder b = new SAXBuilder(); //获得java项目路径 
				        	//String path = ServletActionContext.getRequest().getRealPath("/"); 
				        	//获得XML文件 
				        	Document docred = b.build(new InputSource(MainActivity.class.getClassLoader().getResourceAsStream("NewFile.xml")));
				        	
				        	Element ee = docred.getRootElement();//取根元素 
				        	List<Element> child = ee.getChildren();//
				     //    cname="泸溪";
				        	getSonElement(ee);
				        	url="http://weather.com.cn/html/weather/"+citycode+".shtml";
				        	System.out.println(url);
				        	wv.loadUrl(url);
						}
						catch(Exception e){e.printStackTrace();}
					}//run
				}).start();
				  
			}//oncl
        });
	}

	
	

		    
    public   void getSonElement(Element ele) {
  	  List sons= ele.getChildren();
  	 
  	  if(0!=sons.size()){
  	   for(Iterator i=sons.iterator();i.hasNext();){
  	    Element son=(Element) i.next();
  	   // String sum=count+"  ";
  	    //System.out.print(sum);
  	    if(son.getName().equals("county")&&cityname.equals(""+son.getAttributeValue("name"))){
  	    System.out.println(son.getName()+":"+son.getAttributeValue("weatherCode").trim());
  	    //Weather w = new Weather(son.getAttributeValue("weatherCode")); // 城 市代码 
  	      citycode=son.getAttributeValue("weatherCode").trim().toString();
  	    }
  	    getSonElement(son);
  	   }
  	  }
		
  }
    
    
   
    
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
