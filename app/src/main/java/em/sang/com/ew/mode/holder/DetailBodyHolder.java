package em.sang.com.ew.mode.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import em.sang.com.ew.R;
import em.sang.com.ew.util.JLog;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/28 10:44
 */
public class DetailBodyHolder extends RecyclerView.ViewHolder {

    private View itemView;
    public DetailBodyHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public DetailBodyHolder(int itemId, Context context){
        this(View.inflate(context,itemId,null));

    }

    public void initView(String name,String details){
        TextView tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
        TextView tv_details = (TextView) itemView.findViewById(R.id.tv_item_details);

        if (TextUtils.equals(name,"做法")) {
            StringBuffer sb = null;
            String[] split = details.split("\\s");
            sb = new StringBuffer();
            for (int i = 0;i<split.length;i++ ){
                if (split[i].length()<2){
                    sb.append(split[i]);
                }else {
                    sb.append(split[i]).append("\n\n");
                }
            }
            details = sb.toString();
        }

        JLog.i(details);
        tv_title.setText(name);
        tv_details.setText(details);
    }
}
