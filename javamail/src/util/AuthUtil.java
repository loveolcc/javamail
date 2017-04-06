package util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class AuthUtil {
	public static final String APPID="wx92d234ec4bdc16cf";
	public static final String APPSECRET="5856b567668410cefcfe3e1abfd4da74";
  public static JSONObject getJson(String url) throws ClientProtocolException, IOException{
	  JSONObject jsonObject=null;
	  DefaultHttpClient client=new DefaultHttpClient();
	  HttpGet httpGet=new HttpGet(url);
	  HttpResponse response=client.execute(httpGet);
	  HttpEntity entity=response.getEntity();
	  if (entity!=null) {
		String results=EntityUtils.toString(entity,"UTF-8");
		jsonObject=JSONObject.fromObject(results);
	}
	  httpGet.releaseConnection();
	  return jsonObject;  
  }
}
