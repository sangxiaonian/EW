package em.sang.com.ew.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/25 18:05
 */
public class DetailsPagerAdapter extends PagerAdapter {

    private final Context cntext;
    private List<SearchBean.ShowapiResBodyBean.CbListBean.ImgListBean> lists = new ArrayList<>();

    public DetailsPagerAdapter(List<SearchBean.ShowapiResBodyBean.CbListBean.ImgListBean> lists, Context context){
        this.cntext=context;
        this.lists=lists;
    }

    @Override
    public int getCount() {

        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =   View.inflate(cntext, R.layout.item_heard_details,null);
        ImageView img = (ImageView) view.findViewById(R.id.img_details);
        Glide.with(cntext).
                load(lists.get(position).imgUrl)
                .placeholder(R.mipmap.defaul)
                .error(R.mipmap.ic_launcher)//load失敗的Drawable
                .centerCrop()
                .into(img);
        container.addView(view);
        return view;

    }
}
