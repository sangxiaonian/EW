package em.sang.com.ew.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import em.sang.com.ew.R;
import em.sang.com.ew.adapter.DetailsAdapter;
import em.sang.com.ew.adapter.DetailsPagerAdapter;
import em.sang.com.ew.basic.BasicActivity;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.JLog;

public class DetailsActivity extends BasicActivity {
    SearchBean.ShowapiResBodyBean.CbListBean data;

    private ViewPager vp;
    private CollapsingToolbarLayout cb;
    private RecyclerView rc;
    private Toolbar toolbar;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();
        initDatas();
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_details);
        vp = (ViewPager) findViewById(R.id.vp_details);
        rc= (RecyclerView) findViewById(R.id.rc_details);

        cb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        cb.setExpandedTitleColor(Color.WHITE);
        cb.setCollapsedTitleTextColor(Color.WHITE);

        ll= (LinearLayout) findViewById(R.id.ll_details);

    }

    @Override
    public void initDatas() {
        data = getIntent().getParcelableExtra("details");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        JLog.i(data.toString());
        for (int i = 0; i < data.imgList.size(); i++) {
            View view = View.inflate(this, R.layout.item_point, null);
            if (i == 0) {
                ((ImageView) view.findViewById(R.id.img_point)).setImageResource(R.drawable.point_white);
            }else {
                ((ImageView) view.findViewById(R.id.img_point)).setImageResource(R.drawable.point_gray);
            }

            ll.addView(view);
        }
        vp.setAdapter(new DetailsPagerAdapter(data.imgList,this));
        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                JLog.i(position+"------------------");
                for (int i = 0; i <ll.getChildCount() ; i++) {
                    if (i == position) {
                        ((ImageView) ll.getChildAt(i).findViewById(R.id.img_point)).setImageResource(R.drawable.point_white);
                    }else {
                        ((ImageView) ll.getChildAt(i).findViewById(R.id.img_point)).setImageResource(R.drawable.point_gray);
                    }

                }

            }


        });

        cb.setTitle(data.name);
        StaggeredGridLayoutManager manager =new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(new DetailsAdapter(data,this));
    }


}
