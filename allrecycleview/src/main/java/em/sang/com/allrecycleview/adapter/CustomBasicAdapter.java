package em.sang.com.allrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.CustomRecycleListener;


/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/7 16:43
 */
public class CustomBasicAdapter<T> extends RecyclerView.Adapter {
    public List<T> lists = new ArrayList<>();
    public int itemID;
    public List<CustomBasicHolder> heards = new ArrayList<CustomBasicHolder>();
    public List<CustomBasicHolder> foots = new ArrayList<CustomBasicHolder>();
    public CustomRecycleListener<T> listener;
    public Context context;
    public final int BODY = Integer.MAX_VALUE;

    public CustomBasicAdapter() {
    }

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
        CustomBasicHolder holder = listener.getHolderByViewType(context, lists, itemID);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < heards.size()) {
            ((CustomBasicHolder) holder).initView(position,context);

        } else if (position < heards.size() + lists.size()) {
            ((CustomBasicHolder) holder).initView(position - heards.size(),context);
            ;
        } else {
            ((CustomBasicHolder) holder).initView(position - heards.size() - lists.size(),context);
        }

    }

    @Override
    public int getItemCount() {
        return lists.size() + heards.size() + foots.size();
    }


    /**
     * 添加头布局
     *
     * @param heardHolder 头布局的holder
     */
    public void addHead(CustomBasicHolder heardHolder) {
        heards.add(heardHolder);
    }

    /**
     * 添加脚布局
     *
     * @param footHolder
     */
    public void addFoots(CustomBasicHolder footHolder) {
        foots.add(footHolder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeader(position) || isFooter(position))
                            ? gridManager.getSpanCount() : 1;
                }


            });
        }
    }

    private boolean isFooter(int position) {
        return position >= heards.size() + lists.size() && position < heards.size() + lists.size() + foots.size();
    }

    public boolean isHeader(int position) {
        return position >= 0 && position < heards.size();
    }
}
