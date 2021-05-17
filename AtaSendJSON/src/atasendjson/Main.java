
package atasendjson;

/**
 *
 * @author resul
 */
public class Main {
    public static void main(String[] args) {
        String login = "Resul";
        String pass = "R9990907";
        String text = "salam";
        String msisdn = "994709990907";
        String sender = "Klinika";
        String req_num = "269";
        
        
        MD5 md = new MD5();
       // String key = md.getMd5("R9990907");
        String key = md.getMd5(md.getMd5(pass)+login+text+msisdn+sender);
        System.out.println(key);
    }
}
