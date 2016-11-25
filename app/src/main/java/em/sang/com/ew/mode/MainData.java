package em.sang.com.ew.mode;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import em.sang.com.ew.basic.BasicBean;
import em.sang.com.ew.http.HttpParams;
import em.sang.com.ew.http.HttpServer;
import em.sang.com.ew.http.RetrofitUtils;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.mode.inter.IMainData;
import em.sang.com.ew.presenter.MainPresenter;
import em.sang.com.ew.util.ConfigUtlis;
import em.sang.com.ew.util.JLog;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:22
 */
public class MainData implements IMainData {

    private List<BasicBean> lists = new ArrayList<>();
    private MainPresenter presenter;
    private String[] searchs = {"炒", "炸", "蒸", "煮", "炖", "排骨", "面", "家常", "汤", "水果", "鸡翅", "牛肉", "奶"};


    public MainData(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<BasicBean> getData() {
        return lists;
    }

    @Override
    public void initData() {
        int index = new Random().nextInt(searchs.length);
        getSearch(searchs[index]);
    }


    public void getSearch(String name) {
        HttpServer server = RetrofitUtils.getInstance().getClient(ConfigUtlis.getConfig("search"));
        HttpParams params = new HttpParams();

        params.put("name", name);
        JLog.i(params.toString());
        Observable<SearchBean> details = server.getSearch(params.getParams());
        details.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .filter(new Func1<SearchBean, Boolean>() {
                    @Override
                    public Boolean call(SearchBean searchBean) {

                        return searchBean.showapi_res_code == 0 && searchBean.showapi_res_body != null&&searchBean.showapi_res_body.cbList!=null;
                    }
                }).map(new Func1<SearchBean, List<SearchBean.ShowapiResBodyBean.CbListBean>>() {
                    @Override
                    public List<SearchBean.ShowapiResBodyBean.CbListBean> call(SearchBean searchBean) {
                        return searchBean.showapi_res_body.cbList;
                    }
                })
                .flatMap(new Func1<List<SearchBean.ShowapiResBodyBean.CbListBean>, Observable<SearchBean.ShowapiResBodyBean.CbListBean>>() {
                    @Override
                    public Observable<SearchBean.ShowapiResBodyBean.CbListBean> call(List<SearchBean.ShowapiResBodyBean.CbListBean> cbListBeen) {
                        return Observable.from(cbListBeen);
                    }
                }).filter(new Func1<SearchBean.ShowapiResBodyBean.CbListBean, Boolean>() {
            @Override
            public Boolean call(SearchBean.ShowapiResBodyBean.CbListBean cbListBean) {
                return cbListBean.imgList!=null&&cbListBean.imgList.size()>0&& TextUtils.isEmpty(cbListBean.cbId);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter);
    }


}
