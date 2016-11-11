package em.sang.com.allrecycleview.inter;

import android.content.Context;
import android.view.View;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/8 16:10
 */
public class DefaultRecycleViewLisenter<T> implements CustomRecycleListener<T> {
    @Override
    public void initView(T data, View itemView) {

    }

    @Override
    public int getItemTypeByPosition() {
        return 0;
    }

    @Override
    public CustomBasicHolder getHolderByViewType(Context context, List<T> lists, int itemID) {
        return null;
    }

    /**
     * 获取头布局holder
     * @param context 上下文
     * @param position  位置
     * @return holder
     */
    public CustomBasicHolder getHeardHolder(Context context, int position){return null;}
    /**
     * 获取普通布局holder
     * @param context 上下文
     * @param lists 数据
     * @param itemID 布局ID
     * @return holder
     */
    public CustomBasicHolder getBodyHolder(Context context, List<T> lists, int itemID){return null;}
    /**
     * 获取脚局holder
     * @param context 上下文
     * @param position 脚布局位置,从0开始
     * @return holder
     */
    public CustomBasicHolder getFootdHolder(Context context, int position){return null;}
}