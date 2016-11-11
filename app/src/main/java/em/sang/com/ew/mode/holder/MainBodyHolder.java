package em.sang.com.ew.mode.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.DeviceUtils;

import static android.R.attr.data;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/10 14:48
 */
public class MainBodyHolder<T> extends CustomBasicHolder {

    public MainBodyHolder(View itemView) {
        super(itemView);
    }

    public MainBodyHolder(List<T> datas, View itemView) {
        super(datas, itemView);
    }

    public MainBodyHolder(Context context, List<T> lists, int itemID) {
        super(context, lists, itemID);
    }

    @Override
    public View initView(int position,Context context) {
        TextView textView = (TextView) itemView.findViewById(R.id.tv_mian);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_main);
        SearchBean.ShowapiResBodyBean.CbListBean data = (SearchBean.ShowapiResBodyBean.CbListBean) datas.get(position);
        Glide.with(context).
                load((data).imgList.get(0).imgUrl)
                .placeholder(R.mipmap.defaul)
                .error(R.mipmap.ic_launcher)//load失敗的Drawable
                .centerCrop()
                .into(imageView);
        textView.setText(data.name);
        return itemView;
    }
}
