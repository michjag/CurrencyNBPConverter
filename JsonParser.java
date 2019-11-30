package currencyconverternbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }

		  public double start(String curr) throws IOException, JSONException 
		  {
			double exchange = 0.0;  
			
			if(curr.equalsIgnoreCase("pln"))
			{
				return exchange = 1.0;
			}
			else
			{
				JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/"+ curr + "//?format=json");
			    //System.out.println(json.toString());
			    System.out.println(json.get("code"));
			    exchange = (double)((JSONObject)((JSONArray)json.get("rates")).get(0)).get("mid");
			    
			    return exchange;
			}
		  }
		  
		  public String exchangeDate(String curr) throws IOException, JSONException
		  {
			  if (curr.equalsIgnoreCase("pln"))
				  curr = "eur";
			   
			  JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/"+ curr + "//?format=json");
			  String exDate;
			  exDate = (String)((JSONObject)((JSONArray)json.get("rates")).get(0)).get("effectiveDate");
			  return exDate;
		  }
		  
		  /*
		  public void start() throws IOException, JSONException // String[] args
		  {
		    JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/eur/?format=json");
		    //System.out.println(json.toString());
		    System.out.println(json.get("code"));
		    System.out.println(((JSONObject)((JSONArray)json.get("rates")).get(0)).get("mid"));
		       
		  } 
		  */
	
}
