package em.sang.com.allrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import em.sang.com.allrecycleview.PullRecycleView;
import em.sang.com.allrecycleview.holder.SimpleHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;


/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/7 16:43
 */
public class RefrushAdapter<T> extends DefaultAdapter<T> {


    public RefrushAdapter(Context context, List lists, int itemID, DefaultRecycleViewLisenter lisenter) {
        super(context, lists, itemID, lisenter);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView instanceof PullRecycleView){
            View topView = ((PullRecycleView) recyclerView).getTopView();

            addTop(new SimpleHolder(topView));
        }
        super.onAttachedToRecyclerView(recyclerView);
    }
}
