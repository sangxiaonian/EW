package em.sang.com.ew.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import em.sang.com.allrecycleview.adapter.DefaultAdapter;
import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.allrecycleview.inter.DefaultRecycleViewLisenter;
import em.sang.com.ew.R;
import em.sang.com.ew.basic.BasicActivity;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.mode.holder.SearchBodyHolder;
import em.sang.com.ew.presenter.IMainPresenter;
import em.sang.com.ew.presenter.MainPresenter;
import em.sang.com.ew.ui.inter.IMainView;

public class SearchActivity extends BasicActivity implements IMainView{

    private Toolbar toolbar;
    private TextView tv;
    private RecyclerView recyclerView;
    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initDatas();
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_search);
        tv= (TextView) findViewById(R.id.tv_title_search);
        recyclerView = (RecyclerView) findViewById(R.id.rc_search);
        LinearLayoutManager manager = new LinearLayoutManager(mContext );
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void initDatas() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

         presenter = new MainPresenter(this);
        presenter.getTranData(this,"search");

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void showLoding() {

    }

    @Override
    public void setAdapter(List<SearchBean.ShowapiResBodyBean.CbListBean> datas) {
        DefaultAdapter<SearchBean.ShowapiResBodyBean.CbListBean> adapter = new DefaultAdapter<SearchBean.ShowapiResBodyBean.CbListBean>(mContext, datas, R.layout.item_search, new DefaultRecycleViewLisenter<SearchBean.ShowapiResBodyBean.CbListBean>() {
            @Override
            public CustomBasicHolder getBodyHolder(Context context, final List<SearchBean.ShowapiResBodyBean.CbListBean> lists, int itemID) {
                return new SearchBodyHolder<SearchBean.ShowapiResBodyBean.CbListBean>(context, lists, itemID) {
                    @Override
                    public void onClickHolder(SearchBean.ShowapiResBodyBean.CbListBean id) {
                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("details", id);
                        startActivity(intent);
                    }
                };
            }
        });

        recyclerView.setAdapter(adapter);
    }

}
