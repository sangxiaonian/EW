package em.sang.com.ew.mode.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import em.sang.com.allrecycleview.holder.HeardHolder;
import em.sang.com.ew.R;
import em.sang.com.ew.http.HttpParams;
import em.sang.com.ew.http.HttpServer;
import em.sang.com.ew.http.RetrofitUtils;
import em.sang.com.ew.mode.bean.DetailsBean;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.ConfigUtlis;
import em.sang.com.ew.util.DeviceUtils;
import em.sang.com.ew.util.JLog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(((SearchBean.ShowapiResBodyBean.CbListBean) data).cbId);
            }
        });


        return itemView;
    }


    public void details(String id){
        HttpServer server = RetrofitUtils.getInstance().getClient(ConfigUtlis.getConfig("detial"));
        HttpParams params = new HttpParams();
        params.put("cbId",id);
        server.getDetails(params.getParams()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<DetailsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(DetailsBean detailsBean) {
                JLog.i("成功");
            }
        });
    }


}
