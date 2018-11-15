import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestScenario001 {
    @Test
    public void test001(){
        System.out.println("sample test 001");
        String url="https://reqres.in/api/users/2";
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGetRequest=new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse=httpClient.execute(httpGetRequest);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            System.out.println("status code "+statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPOST() {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.technicalkeeda.com/post-request");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("name", "Yashwant"));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);

            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("**POST** request Url: " + post.getURI());
            System.out.println("Parameters : " + nameValuePairs);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:-\n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOST_ReqRes_Users() {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://reqres.in/api/users");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("name", "venky"));
            nameValuePairs.add(new BasicNameValuePair("job", "leader"));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);

            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("**POST** request Url: " + post.getURI());
            System.out.println("Parameters : " + nameValuePairs);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:-\n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOST_ReqRes_Users_JSONObject() {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://reqres.in/api/users");
        try {

            //String jsonString1 = "[{\"Name\":\"EB DAVIE ST FS HOWE ST\",\"Latitude\":49.27755,\"Longitude\":-123.12698,\"Routes\":\"006, C23\"}]";
            String jsonString="[{\"name\":\"bsanti\",\"job\":\"testqa,testlead\"}]";
            JSONArray arrayJSONObject=new JSONArray(jsonString);
            JSONObject jsonObject=arrayJSONObject.getJSONObject(0);
            String jsonData=jsonObject.toString();
            System.out.println("jsonData- "+jsonData);
            StringEntity stringEntity=new StringEntity(jsonString);
            stringEntity.setContentType("application/json");
            post.setEntity(stringEntity);
            HttpResponse response = client.execute(post);

            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("**POST** request Url: " + post.getURI());
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:-\n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testJSONObject() throws UnsupportedEncodingException {
        JSONObject json = new JSONObject();
        json.put("student", "santi");
        json.put("subjects", "abc");
        StringEntity params = new StringEntity(json.toString());
        System.out.println("hello after params");
    }
@Test
    public void testCase_Jsondata() throws Exception {

        String jsonString = "[{\"Name\":\"EB DAVIE ST FS HOWE ST\",\"Latitude\":49.27755,\"Longitude\":-123.12698,\"Routes\":\"006, C23\"}]";
        JSONArray arrayObject = new JSONArray(jsonString);
        JSONObject inner = arrayObject.getJSONObject(0);
        String[] keys = JSONObject.getNames(inner);

        for(String entry : keys) {
            System.out.println(entry+" : "+inner.get(entry));
        }

    }

    @Test
    public void test_json2stringkeys(){
        String responseString="[{\n" +
                "    \"Name\": \"EB DAVIE ST FS HOWE ST\",\n" +
                "    \"Latitude\": 49.27755,\n" +
                "    \"Longitude\": -123.12698,\n" +
                "    \"Routes\": \"006, C23\"   }]";
        JSONArray jsonArrayResponse=new JSONArray(responseString);
        System.out.println("jsonArrayResponse.length() - "+jsonArrayResponse.length());
        for(int jsonResponseCounter=0;jsonResponseCounter<jsonArrayResponse.length();jsonResponseCounter++){
            System.out.println("jsonobject string - "+jsonArrayResponse.getJSONObject(jsonResponseCounter).toString());
            System.out.println("jsonArrayResponse.length() - "+jsonArrayResponse.getJSONObject(jsonResponseCounter).length());
            int keysCounter=0;
            for(String keysName:JSONObject.getNames(jsonArrayResponse.getJSONObject(jsonResponseCounter))){
                String keysValue=jsonArrayResponse.getJSONObject(jsonResponseCounter).get(keysName).toString();
                System.out.println("key # "+keysCounter +" Name, Value - "+keysName +"  "+keysValue);
                if(keysName.equalsIgnoreCase("Routes")){
                    String[] routes=keysValue.split(",");
                    for(int routesCounter=0;routesCounter<routes.length;routesCounter++){
                        System.out.println("route #"+routesCounter +" "+routes[routesCounter]);
                    }
                }
            }
        }
    }
}
