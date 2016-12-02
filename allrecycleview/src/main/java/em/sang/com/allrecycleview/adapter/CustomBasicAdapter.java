package em.sang.com.allrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.CustomRecycleListener;


/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/7 16:43
 */
public class CustomBasicAdapter<T> extends BasicAdapter {


    public CustomBasicAdapter(Context context, List<T> lists, int itemID, CustomRecycleListener<T> listener) {
        this.lists = lists;
        this.itemID = itemID;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (listener != null) {
            return listener.getItemTypeByPosition();
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomBasicHolder holder = null;
        if (listener!=null) {
            holder   = listener.getHolderByViewType(context, lists, itemID);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < heards.size()) {
            ((CustomBasicHolder) holder).initView(position,context);

        } else if (position < heards.size() + lists.size()) {
            ((CustomBasicHolder) holder).initView(position - heards.size(),context);

        } else {
            ((CustomBasicHolder) holder).initView(position - heards.size() - lists.size(),context);
        }

    }



}
