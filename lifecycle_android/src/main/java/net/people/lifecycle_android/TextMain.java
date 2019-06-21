package net.people.lifecycle_android;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by  yahuigao
 * Date: 2019/4/16
 * Time: 7:49 PM
 * Description:
 */
public class TextMain {

    public static void main(String[] args) {
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("access_key", "a335f57f83d778da07baadefafce1a41");
        treeMap.put("ts", "1555383789");
        try {
            String reqSign = getReqSign(treeMap);
            System.out.println(" reqSign " + reqSign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String getReqSign(Map<String, String> resultMap) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();


        Set<Map.Entry<String, String>> entries = resultMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            String key = entry.getKey();
            String val = value;
            sb.append(key).append("=").append(val).append("&");
        }
        sb.append("app_key").append("=").append("1d8b6e7d45233436");
        return MD5.md5(sb.toString()).toUpperCase();
    }
}
