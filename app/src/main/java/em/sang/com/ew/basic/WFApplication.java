package em.sang.com.ew.basic;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Hashtable;

import em.sang.com.ew.util.ConfigUtlis;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/9 10:10
 */
public class WFApplication extends Application {
    /**
     * 配置信息
     */
    public Hashtable<String ,String> property = new Hashtable<>();

    /**
     * 加载配置信息
     * @param fileName 配置信息名称
     */
    public void loadProperty(String fileName){
       property= ConfigUtlis.getInstance().loadConfig(fileName,this);
    }

    /**
     * 获取配置信息
     * @return
     */
    public Hashtable<String ,String> getProperty(){
        return property;
    }

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

    }

    /**
     * 获取Application的Context
     * @return
     */
    public static Context getAppContext(){
        return context;
    }
}
