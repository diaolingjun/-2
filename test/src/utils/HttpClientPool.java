package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpClientPool {
/**
 * 这是httpClient连接池
 * @throws Exception 
 */
	public static void HttpClientPool() {
		//创建连接池管理器
		PoolingHttpClientConnectionManager cm =new  PoolingHttpClientConnectionManager();
		
		
		//设置最大连接数
		cm.setMaxTotal(100);
		//设置每个主机的最大连接数
		cm.setDefaultMaxPerRoute(10);
		//使用连接池管理器发起请求
//		doGet(cm);
//		doPost(cm);
	}

public static String doPost(PoolingHttpClientConnectionManager cm) throws Exception {
	//从连接池中获取httpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

      	//2、输入网址,发起请求，创建httpPost对象
    	  HttpPost httpPost= new HttpPost("http://m.sinovision.net/newpneumonia.php");
    	  System.out.println("发起请求的信息："+httpPost);
    	  
    	  //Post使用，声明List集合，封装表单中的参数
    	  List<NameValuePair> params= new ArrayList<NameValuePair>();
    	  params.add(new BasicNameValuePair("",""));
    	  
    	  //创建表单的Entity对象,第一个参数是封装好的参数，第二个是编码
    	  UrlEncodedFormEntity formEntity= new UrlEncodedFormEntity(params,"utf8");
    	  
    	  //设置表单的Entity对象到Post请求中
    	  httpPost.setEntity(formEntity);
    	  
    	  
    	//配置请求信息
    	  RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//设置创建连接的最长时间，单位为毫秒
    	  .setConnectionRequestTimeout(500)//设置获取连接的最长时间，单位为毫秒
    	  .setSocketTimeout(10*1000)//设置传输数据的最长时间，单位为毫秒
    	  .build();
    	  //给请求设置请求信息
    	  httpPost.setConfig(config);
    	  
    	  CloseableHttpResponse response=null;
    	  String content=null;
    	  try {
    	  //3、按回车，发起请求，返回响应，使用httpClient对象发起请求
    	   response = httpClient.execute(httpPost);
    	  //解析响应，获取数据
    	  //判断状态码是否为两百
    	  if(response.getStatusLine().getStatusCode()==200) {
    		  HttpEntity httpEntity = response.getEntity();
    		  if(httpEntity!=null) {
    	          content = EntityUtils.toString(httpEntity, "utf8");
    			  System.out.println(content.length());
//    			  System.out.println(content);
    			  }
    	  }else {
    		  System.out.println("请求失败"+response);
    	  }
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }finally {
    
    		  try {
    			//关闭response
    			  if(response!=null) {
    				  //关闭response 
    				  response.close();
    			  }
				//不关闭httpClient
				//httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
		  return content;    	  	
}

public static String doGet(PoolingHttpClientConnectionManager cm) throws Exception {
	//从连接池中获取httpClient对象
	CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
	//创建URIBuilder
	  URIBuilder uribuilder= new URIBuilder("http://m.sinovision.net/newpneumonia.php");
	  //设置参数：参数名+参数值,可设置多个
	  uribuilder.setParameter("","");
	  
	  //2、输入网址,发起请求，创建httpGet对象
	  HttpGet httpGet= new HttpGet(uribuilder.build());
	  System.out.println("发起请求的信息："+httpGet);
	  
	  //配置请求信息
	  RequestConfig config = RequestConfig.custom().setConnectTimeout(1000000000*1000000000)//设置创建连接的最长时间，单位为毫秒
	  .setConnectionRequestTimeout(1000000000*1000000000)//设置获取连接的最长时间，单位为毫秒
	  .setSocketTimeout(1000000000*1000000000)//设置传输数据的最长时间，单位为毫秒
	  .build();
	  //给请求设置请求信息
	  httpGet.setConfig(config);
	  
	  
	  
	  
	  
	  CloseableHttpResponse response=null;
	  String content=null;
	  try {
	  //3、按回车，发起请求，返回响应，使用httpClient对象发起请求
	   response = httpClient.execute(httpGet);
	  //解析响应，获取数据
	  //判断状态码是否为两百
	  if(response.getStatusLine().getStatusCode()==200) {
		  HttpEntity httpEntity = response.getEntity();
		  if(httpEntity!=null) {
          content = EntityUtils.toString(httpEntity, "utf8");
//		  System.out.println(content.length());
//		  System.out.println(content);
		  }
	  }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }finally {

		  try {
			  if(response!=null) {
				  //关闭response 
				  response.close();
			  }
			//不能关闭httpClient
			//httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  return content; 
}
}