package em.sang.com.ew.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import em.sang.com.ew.mode.bean.SearchBean;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/25 18:05
 */
public class DetailsAdapter extends PagerAdapter {

    private List<SearchBean.ShowapiResBodyBean.CbListBean.ImgListBean> lists = new ArrayList<>();

    public DetailsAdapter (List<SearchBean.ShowapiResBodyBean.CbListBean.ImgListBean> lists){
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


}
