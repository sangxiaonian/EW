package em.sang.com.allrecycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import em.sang.com.allrecycleview.adapter.BasicAdapter;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/12/1 16:17
 */
public class RefrushTopAndFootRecycleView extends PullRecycleView {

    public RefrushTopAndFootRecycleView(Context context) {
        super(context);
    }

    public RefrushTopAndFootRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefrushTopAndFootRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    private BasicAdapter adapter;

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

//        JLog.e(adapter.getItemCount()+"状态:"+state);
        if (state== RecyclerView.SCROLL_STATE_DRAGGING){//拖拽滑动
            if (isFirst()&&isUpScroll){
//
            }

        }else if (state==RecyclerView.SCROLL_STATE_IDLE){//停止拖动

        }else if(RecyclerView.SCROLL_STATE_SETTLING==state){//惯性滑动

       }


    }








}
