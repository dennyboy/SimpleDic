package utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dennis on 9/14/2015.
 */
public class HttpHandler {


    /**
     * Returns Get request via a string
     * @param targetURL URL in string
     * @return
     */
    public static String executeGet(String targetURL) {
        HttpURLConnection httpURLConnection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);

            httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("Accept-Language", "ko-KR");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.connect();

            //Get Response
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String response = "";
            String line;
            while((line = rd.readLine()) != null) {
                response += line;
            }
            rd.close();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }





}
