package em.sang.com.allrecycleview.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/7 16:46
 */
public  class CustomBasicHolder<T> extends RecyclerView.ViewHolder {


    public Context context;
    public View itemView;
    public List<T> datas;
    public int itemId;


    public CustomBasicHolder(View itemView) {
        super(itemView);
    }

    public CustomBasicHolder(List<T> datas,View itemView ){
        this(itemView);
        this.datas = datas;
        this.itemView = itemView;

    }

    public CustomBasicHolder(Context context, List<T> lists, int itemID) {
        this(lists,LayoutInflater.from(context).inflate(itemID,null));
        this.context =context;
    }

    /**
     * 初始化holder中的数据
     * @param position
     * @return
     */
    public View initView(int position,Context context) {
        return null;
    }
}
