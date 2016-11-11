package em.sang.com.ew.mode.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import em.sang.com.allrecycleview.holder.HeardHolder;
import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.DeviceUtils;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/10 14:49
 */
public class MainMoreHolder extends HeardHolder{



    public MainMoreHolder(SearchBean.ShowapiResBodyBean.CbListBean data, View itemView) {
        super(data, itemView);
    }

    public MainMoreHolder(Context context, SearchBean.ShowapiResBodyBean.CbListBean data, int itemID){
        this(data,View.inflate(context,itemID,null));
        this.context = context;
    }

    @Override
    public View initView(int position,Context context) {

        return itemView;
    }
}
