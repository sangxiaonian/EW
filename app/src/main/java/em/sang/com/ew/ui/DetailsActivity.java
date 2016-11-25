package em.sang.com.ew.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import em.sang.com.ew.R;
import em.sang.com.ew.adapter.DetailsAdapter;
import em.sang.com.ew.mode.bean.SearchBean;
import em.sang.com.ew.util.JLog;

public class DetailsActivity extends AppCompatActivity {
    SearchBean.ShowapiResBodyBean.CbListBean data;

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        data = getIntent().getParcelableExtra("details");
        JLog.i(data.toString());
        vp = (ViewPager) findViewById(R.id.vp_details);
        vp.setAdapter(new DetailsAdapter(data.imgList,this));
    }
}
