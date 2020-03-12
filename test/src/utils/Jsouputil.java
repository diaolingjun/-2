package utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.dao;
import entity.Info;
public class Jsouputil {

	/**
	 * 使用Selector选择器获取元素
	 */
	public static void testSelector()throws Exception{
		//获取Document对象
		HttpClientPool httpClient =new HttpClientPool();
		//创建连接池管理器
		PoolingHttpClientConnectionManager cm =new  PoolingHttpClientConnectionManager();
		//获取网页HTML字符串
		String content=httpClient.doGet(cm);
						
		//解析字符串
		Document doc = Jsoup.parse(content);
//		System.out.println(doc.toString());
	
		//[attr=value],利用属性获取
		Elements elements = doc.select("div[class=todaydata] ").select("div[class=prod]");
		System.out.println(elements.toString());
		Info info=new Info();
		dao dao=new dao();
		for(Element ele:elements) {
			String province=ele.select("span[class=area]").text();
			String Confirmed_num=ele.select("span[class=confirm]").text();
			String Dead_num=ele.select("span[class=dead]").text();
			String Cured_num=ele.select("span[class=cured]").text();
			info=new Info(province,Confirmed_num,Dead_num,Cured_num);
			dao.add(info);
		}
		
	}
}