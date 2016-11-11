package em.sang.com.allrecycleview.inter;


import android.content.Context;
import android.view.View;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;

public interface CustomRecycleListener<T> {

    void initView(T data,View itemView);

    /**
     * 根据position获取itemType
     * @return itemType
     */
    int getItemTypeByPosition();

    /**
     * 根据类型获取相应的ViewHolder
     * @param context 上下文
     * @param lists 数据集合
     * @param itemID 布局ID
     * @return
     */
    CustomBasicHolder getHolderByViewType(Context context, List<T> lists, int itemID);
}
