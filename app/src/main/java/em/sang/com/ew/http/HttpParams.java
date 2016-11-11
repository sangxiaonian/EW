package em.sang.com.ew.http;

import android.text.TextUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import em.sang.com.ew.util.AppUtils;
import em.sang.com.ew.util.ConfigUtlis;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Description：
 *
 * @Author桑小年
 * @Data：2016/11/10 10:47
 */
public class HttpParams {



    private Map<String,String> params ;

    public HttpParams() {
        params=new TreeMap<>();
        params.put("showapi_appid", ConfigUtlis.getConfig("appId"));
        params.put("showapi_sign", ConfigUtlis.getConfig("key"));
    }

    public Map<String,String> getParams(){
        return params;
    }

    public void put(String key,String value){
        params.put(key,value);
    }

    public void putAll(Map<String,String> params){
        this.params.putAll(params);
    }

    public void remove(String key){
        params.remove(key);
    }

    @Override
    public String toString() {

        return encodParams();
    }

    private String encodParams(){
        StringBuffer sb = new StringBuffer();

        Set<String> set = params.keySet();
        for (String key:set) {
            String value = params.get(key);
            if (!TextUtils.isEmpty(value)) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        return sb.toString();
    }
}
