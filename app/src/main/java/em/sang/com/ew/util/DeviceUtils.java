package em.sang.com.ew.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/10 17:37
 */
public class DeviceUtils {


    /**
     * 获取屏幕宽高
     * @param context 上下文
     * @return
     */
    public static int[] getDeviceWidth(Context context){



        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
//        this.density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
//        this.scaledDensity = dm.scaledDensity;
        densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320

        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）



        int screenWidth  = (int)(dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）
        int screenHeight = (int)(dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）

        int[] screens ={screenWidth,screenHeight};

        return screens;

    }



}
