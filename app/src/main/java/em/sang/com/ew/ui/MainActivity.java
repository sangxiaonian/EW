package em.sang.com.ew.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;
import em.sang.com.ew.R;
import em.sang.com.allrecycleview.adapter.DefaultAdapter;
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

public class MainActivity extends BasicActivity implements IMainView {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private IMainPresenter pre;
    private DefaultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((WFApplication) getApplicationContext()).loadProperty("config.properties");


    }


    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        recyclerView = (RecyclerView) findViewById(R.id.menu_rc);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_search) {
                    ToastUtil.showTextToast(mContext, "寻找按钮被点击了");
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showTextToast(mContext, "更多");
            }
        });
    }

    @Override
    public void intDatas() {
        super.intDatas();
        pre = new MainPresenter(this);
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
        List<CbListBean> heards = new ArrayList<>();
        heards.add(lists.get(index));
        lists.remove(index);
        adapter = new DefaultAdapter<CbListBean>(mContext, lists, R.layout.item_main, new DefaultRecycleViewLisenter<CbListBean>(){
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
        adapter.addHead(new MainHeaderHolder (this, heards.get(0), R.layout.item_heard));
        adapter.addHead(new MainMoreHolder(this, null, R.layout.item_more));
        LinearLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DefaultGrideCutLine(this,R.drawable.item_cut));
        recyclerView.setAdapter(adapter);
    }

}

