package entity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Web {
	static final Log logger = LogFactory.getLog(Web.class);
      public static void main(String[] args) throws Exception  {
    	  

    	//1、打开浏览器，创建httpClient对象
    	  CloseableHttpClient httpClient = HttpClients.createDefault();
    	  
    	  //创建URIBuilder
    	  URIBuilder uribuilder= new URIBuilder("https://www.qidian.com");
    	  //设置参数：参数名+参数值,可设置多个
    	  uribuilder.setParameter("key","xuanhuan").setParameter("", "");
    	  
    	//2、输入网址,发起请求，创建httpGet对象
    	  HttpGet httpGet= new HttpGet(uribuilder.build());
    	  System.out.println("发起请求的信息："+httpGet);
    	  
    	  CloseableHttpResponse response=null;
    	  try {
    	  //3、按回车，发起请求，返回响应，使用httpClient对象发起请求
    	   response = httpClient.execute(httpGet);
    	  //解析响应，获取数据
    	  //判断状态码是否为两百
    	  if(response.getStatusLine().getStatusCode()==200) {
    		  HttpEntity httpEntity = response.getEntity();
    		  String content = EntityUtils.toString(httpEntity, "utf8");
    		  System.out.println(content.length());
//    		  System.out.println(content);
    	  }else {
    		  System.out.println("请求失败"+response);
    	  }
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }finally {
    
    		  try {
    			//关闭response
				response.close();
				//关闭httpClient
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  
	}


}