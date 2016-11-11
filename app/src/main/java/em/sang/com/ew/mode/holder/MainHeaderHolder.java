package em.sang.com.ew.mode.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.util.concurrent.ExecutionException;

import em.sang.com.allrecycleview.holder.HeardHolder;
import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.DeviceUtils;
import em.sang.com.ew.util.JLog;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/10 14:49
 */
public class MainHeaderHolder  extends HeardHolder{



    public MainHeaderHolder(SearchBean.ShowapiResBodyBean.CbListBean data, View itemView) {
        super(data, itemView);
    }

    public MainHeaderHolder(Context context, SearchBean.ShowapiResBodyBean.CbListBean data, int itemID){
        this(data,View.inflate(context,itemID,null));
        this.context = context;
    }

    @Override
    public View initView(int position,Context context) {
     ImageView img = (ImageView) itemView.findViewById(R.id.img_heard);

            Glide.with(context).
                    load(((SearchBean.ShowapiResBodyBean.CbListBean) data).imgList.get(0).imgUrl)
                    .override(DeviceUtils.getDeviceWidth(context)[0],DeviceUtils.getDeviceWidth(context)[0]*4/7)
                    .placeholder(R.mipmap.defaul)
                    .error(R.mipmap.ic_launcher)//load失敗的Drawable
                    .centerCrop()
                    .into(img);
        TextView textView = (TextView) itemView.findViewById(R.id.tx_heard);
        textView.setText(((SearchBean.ShowapiResBodyBean.CbListBean)data).name);
        return itemView;
    }
}
