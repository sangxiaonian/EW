package em.sang.com.allrecycleview.holder;

import android.content.Context;
import android.view.View;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/8 11:58
 */
public class HeardHolder<T> extends CustomBasicHolder {

    public View itemView;
    public T data;

    public HeardHolder(T data, View itemView) {
        super(itemView);
        this.data = data;
        this.itemView = itemView;
    }

    public HeardHolder(Context context,T data, int itemID){
        this(data,View.inflate(context,itemID,null));
    }

}
