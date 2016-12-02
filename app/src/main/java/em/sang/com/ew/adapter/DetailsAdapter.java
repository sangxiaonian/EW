package em.sang.com.ew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean.ShowapiResBodyBean.CbListBean;
import em.sang.com.ew.mode.holder.DetailBodyHolder;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/28 10:13
 */
public class DetailsAdapter extends RecyclerView.Adapter {

    private final int IMG = 1;

    private CbListBean data;
    private Context context;

    private List<String> datas,names;

    public DetailsAdapter (CbListBean data, Context context){
        this.context=context;
        this.data=data;
        datas = new ArrayList<>();
        names = new ArrayList<>();
        if (!TextUtils.isEmpty(data.introduce)){
            datas.add(data.introduce);
            names.add("");
        }

        if (!TextUtils.isEmpty(data.type)){
            datas.add(data.type);
            names.add("菜谱");
        }

        if (!TextUtils.isEmpty(data.cl)){
            datas.add(data.cl);
            names.add("用料");
        }
        if (!TextUtils.isEmpty(data.zf)){
            datas.add(data.zf);
            names.add("做法");
        }

        if (!TextUtils.isEmpty(data.knack)){
            datas.add(data.knack);
            names.add("诀窍");
        }

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DetailBodyHolder(R.layout.item_detail,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((DetailBodyHolder) holder).initView(names.get(position ), datas.get(position ));

    }

    @Override
    public int getItemCount() {

            return datas.size();

    }
}
