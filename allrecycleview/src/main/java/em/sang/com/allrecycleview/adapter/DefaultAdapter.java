package em.sang.com.allrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;


/**
 * Description：带有头布局的Adapter
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:50
 */
public class DefaultAdapter<T> extends CustomBasicAdapter {


    public DefaultAdapter(Context context, List<T> lists, int itemID, DefaultRecycleViewLisenter<T> lisenter) {
        super(context, lists, itemID, lisenter);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < tops.size()) {
            return TOP;
        } else if (position < heards.size()+tops.size()) {
            return position;
        } else if (position < heards.size() + lists.size() + tops.size()) {
            return BODY;
        } else if (position<heards.size() + lists.size() + tops.size()+foots.size()){
            return position;
        }else {
            return FOOT;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int id ;
        if (position < tops.size()) {
            id=position;
        } else if (position < heards.size()+tops.size()) {
            id= position-tops.size();
        } else if (position < heards.size() + lists.size() + tops.size()) {
            id=position-heards.size()-tops.size();
        } else if (position<heards.size() + lists.size() + tops.size()+foots.size()){
            id = position-heards.size()-tops.size()-lists.size();
        }else {
            id = position-(heards.size() + lists.size() + tops.size()+foots.size());
        }
        ((CustomBasicHolder) holder).initView(id, context);

    }

    private int topCount,boomCount;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;

        if (viewType == TOP) {
            holder = (RecyclerView.ViewHolder) tops.get(topCount);
            topCount++;
        } else if (viewType < heards.size()+tops.size()) {
            holder= (RecyclerView.ViewHolder) heards.get(viewType-tops.size());
        } else if (viewType == BODY) {
            holder= ((DefaultRecycleViewLisenter) listener).getBodyHolder(context, lists, itemID);
        } else if (viewType<heards.size() + lists.size() + tops.size()+foots.size()){

            holder= (RecyclerView.ViewHolder) foots.get(viewType - heards.size() - lists.size()-tops.size());
        }else if (viewType==FOOT){
            holder = (RecyclerView.ViewHolder) booms.get(boomCount);
            boomCount++;
        }
        return holder;
    }
}
