package em.sang.com.ew.util;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import em.sang.com.ew.basic.WFApplication;

import static android.R.attr.key;
import static android.R.attr.value;

/**
 * Description：加载和操作配置信息的类
 *
 * @Author：桑小年
 * @Data：2016/11/9 10:10
 */
public class ConfigUtlis {

    private static ConfigUtlis configUtlis;
    private Properties properties;
    private ConfigUtlis(){
          properties = new Properties();
    }

    public static ConfigUtlis getInstance() {

        if (configUtlis == null) {
            synchronized (ConfigUtlis.class) {
                if (configUtlis == null) {
                    configUtlis = new ConfigUtlis();
                }
            }
        }

        return configUtlis;
    }


    /**
     * 加载配置信息
     * @param fileName 配置文件名称
     * @param context 上下文
     * @return
     */
    public  Hashtable<String,String> loadConfig(String fileName, Context context) {
        Hashtable<String,String> table = new Hashtable<String,String>();
        try {
            InputStream is = context.getAssets().open(fileName);
            properties.load(is);
            Set<Object> keySet = properties.keySet();

            JLog.i( "执行到了");
            for (Object key : keySet) {
                table.put(key.toString(),properties.get(key).toString());
                JLog.i( key.toString()+" = "+properties.get(key).toString());
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 获取所有配置参数
     * @return
     */
    public static Hashtable<String ,String> getAlltConfig(){
        return  ((WFApplication)WFApplication.getAppContext()).getProperty();
    }

    /**
     * 根据key值获取系统参数
     * @param key key值
     * @return
     */
    public static String getConfig(String key){
        return  getAlltConfig().get(key);
    }


}
