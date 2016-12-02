package em.sang.com.ew.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import em.sang.com.allrecycleview.PullRecycleView;
import em.sang.com.allrecycleview.adapter.DefaultAdapter;
import em.sang.com.allrecycleview.adapter.RefrushAdapter;
import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;
import em.sang.com.ew.R;
import em.sang.com.ew.basic.BasicActivity;
import em.sang.com.ew.basic.WFApplication;
import em.sang.com.ew.mode.bean.SearchBean.ShowapiResBodyBean.CbListBean;
import em.sang.com.ew.mode.holder.MainBodyHolder;
import em.sang.com.ew.mode.holder.MainHeaderHolder;
import em.sang.com.ew.mode.holder.MainMoreHolder;
import em.sang.com.ew.presenter.IMainPresenter;
import em.sang.com.ew.presenter.MainPresenter;
import em.sang.com.ew.ui.inter.IMainView;
import em.sang.com.ew.util.DefaultGrideCutLine;
import em.sang.com.ew.util.ToastUtil;
import em.sang.com.ew.view.SearchView;

public class MainActivity extends BasicActivity implements IMainView {

    private Toolbar toolbar;
    private PullRecycleView recyclerView;
    private IMainPresenter pre;
    private DefaultAdapter<CbListBean> adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WFApplication) getApplicationContext()).loadProperty("config.properties");
        setContentView(R.layout.activity_main);
        initView();
        initDatas();

    }

    List<CbListBean> heards;
    List<CbListBean> lists;
    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        searchView = (SearchView) findViewById(R.id.main_search);
        recyclerView = (PullRecycleView) findViewById(R.id.menu_rc);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showTextToast(mContext, "更多");
            }
        });
        searchView.setOnSearchListener(new SearchView.OnSearchClickListener() {
            @Override
            public void onClick(String msg) {
                if (TextUtils.isEmpty(msg)){
                    ToastUtil.showTextToast(mContext,"没有搜索内容");
                }else {
                    Intent intent = new Intent(mContext, SearchActivity.class);
                    intent.putExtra("search",msg);
                    startActivity(intent);
                }
            }
        });

        heards = new ArrayList<>();
        lists  =new ArrayList<>();
        LinearLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DefaultGrideCutLine(this,R.drawable.device_rc_cut));

    }

    @Override
    public void initDatas() {
        super.initDatas();
        pre = new MainPresenter(this);
        pre.getTranData(this,"search");
    }



    @Override
    public void dismiss() {

    }

    @Override
    public void showLoding() {

    }


    @Override
    public void setAdapter(List<CbListBean> lists) {
        int index = new Random().nextInt(lists.size());
        heards.clear();
        heards.add(lists.get(index));
        lists.remove(index);
        this.lists = lists;
        adapter = new RefrushAdapter<CbListBean>(mContext, lists, R.layout.item_main, new DefaultRecycleViewLisenter<CbListBean>(){
            @Override
            public CustomBasicHolder getBodyHolder(Context context, final List<CbListBean> lists, int itemID) {
                return new MainBodyHolder<CbListBean>(context, lists, itemID){
                    @Override
                    public void onClickHolder(CbListBean id) {

                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("details",id);
                        startActivity(intent);
                    }
                };
            }});


        adapter.addHead(new MainHeaderHolder(this, heards.get(0), R.layout.item_heard){
            @Override
            public void onClickHolder(CbListBean data) {
                super.onClickHolder(data);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("details",data);
                startActivity(intent);
            }
        });
        adapter.addHead(new MainMoreHolder(this, null, R.layout.item_more));

        recyclerView.setAdapter(adapter);
    }

}

