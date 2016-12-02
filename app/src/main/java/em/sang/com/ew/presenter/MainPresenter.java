package em.sang.com.ew.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import em.sang.com.ew.mode.MainData;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.mode.inter.IMainData;
import em.sang.com.ew.ui.inter.IMainView;
import em.sang.com.ew.util.JLog;
import rx.Subscriber;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:26
 */
public class MainPresenter extends Subscriber<SearchBean.ShowapiResBodyBean.CbListBean> implements IMainPresenter {

    private IMainView view;
    private IMainData data;
    private List<SearchBean.ShowapiResBodyBean.CbListBean> cbListBeens;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.data = new MainData(this);
        cbListBeens = new ArrayList<>();

    }

    @Override
    public void onStart() {
        view.showLoding();
    }

    @Override
    public void onCompleted() {
        view.dismiss();
        view.setAdapter(cbListBeens);
    }

    @Override
    public void onError(Throwable e) {
        view.dismiss();
        e.printStackTrace();
    }

    @Override
    public void onNext(SearchBean.ShowapiResBodyBean.CbListBean cbListBeen) {

        if (cbListBeens==null){
            cbListBeens = new ArrayList<>();
        }
        cbListBeens.add(cbListBeen);

    }

    @Override
    public String getTranData(Activity activity, String name) {
        String extra = activity.getIntent().getStringExtra(name);
        data.startSearch(extra);
        JLog.i(extra);
        return extra;
    }
}
