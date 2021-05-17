package atasendjson;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author resul
 */
public class AtaSendJSON {

//gite yukledim
    public static void main(String[] args) {
                 
        try {
            
              /* Start of Fix */
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(X509Certificate[] certs, String authType) { }

        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) { return true; }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /* End of the fix*/
        
        String login = "Selnet";//shukur
        String pass = "net2017sel";//2020shukur2019!@2012
        String text = "Salam";
        String msisdn = "994709990907";
        String sender = "SELNET";//Mobis
        String req_num = "2622009";
        
        
        MD5 md = new MD5();
       // String key = md.getMd5("R9990907");
        String key = md.getMd5(md.getMd5(pass)+login+text+msisdn+sender);
        System.out.println(key);
                
        
String json = "{\"key\":\"8580dfc03aea62507981200b3188f35a\",\"login\":\"shukur\",\"text\":\"salam\",\"msisdn\":\"994709990907\",\"sender\":\"Mobis\",\"req_num\":\"11074\"}";

String json1 = "{\"key\":\""+key+"\",\"login\":\""+login+"\",\"text\":\""+text+"\",\"msisdn\":\""+msisdn+"\",\"sender\":\""+sender+"\",\"req_num\":\""+req_num+"\"}";
        
           
            String host = "https://sms.atatexnologiya.az/index.php?app=json_sms"; 
            URL url = new URL(host);
            URLConnection con = url.openConnection();
            
            // specify that we will send output and accept input
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(2000);  // long timeout, but not infinite
            con.setReadTimeout(200000);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);
          

            // tell the web server what we are sending
            //con.setRequestProperty("Content-Type", "text/json");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(json1);
            writer.flush();
            writer.close();
            
            // reading the response
            InputStreamReader reader = new InputStreamReader(con.getInputStream());
            StringBuilder bilder = new StringBuilder(); 
            
            char[] cbuf = new char[2048];
            int num;
            while (-1 != (num = reader.read(cbuf))) {
                bilder.append(cbuf, 0, num);
            }
            String result = bilder.toString();
            System.err.println("Response: " + result);
        } catch (Throwable t) {
            t.printStackTrace(System.out);
        }
    }
        
        
    }
    