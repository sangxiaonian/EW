package em.sang.com.allrecycleview.utils;

import android.content.Context;
import android.view.View;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/11 15:03
 */
public class Apputils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取控件宽高
     * @param view
     * @return 0为宽
     */
    public static int[] getWidthAndHeight(View view){
        int[] res = new int[2];
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        res[1] = view.getMeasuredHeight();
        res[0]= view.getMeasuredWidth();
        return res;
    }
}
