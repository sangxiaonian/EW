package em.sang.com.ew.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/7 16:14
 */
public abstract class BasicActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
    }



    /**
     * 初始化数据
     */
    public void initDatas() {
    }

    /**
     * 初始化界面数据,在onStart中调用
     */
    public abstract void initView();
}
