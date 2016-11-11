package em.sang.com.allrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.holder.FootHolder;
import em.sang.com.allrecycleview.holder.HeardHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;


/**
 * Description：带有头布局的Adapter
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:50
 */
public class DefaultAdapter<T> extends CustomBasicAdapter {


    public DefaultAdapter(Context context, List<T> lists, int itemID, DefaultRecycleViewLisenter<T> lisenter) {
       super(context,lists,itemID,lisenter);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < heards.size()) {
            return position;
        } else if (position < heards.size() + lists.size()) {
            return BODY;
        } else {
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < heards.size()) {

            return (RecyclerView.ViewHolder) heards.get(viewType);
        } else if (viewType == BODY) {
            return ((DefaultRecycleViewLisenter) listener).getBodyHolder(context,lists,itemID);
        } else {
            return (RecyclerView.ViewHolder) foots.get(viewType-heards.size()-lists.size());
        }
    }
}
