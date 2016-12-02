package em.sang.com.ew.mode.holder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import em.sang.com.allrecycleview.holder.CustomBasicHolder;
import em.sang.com.ew.R;
import em.sang.com.ew.mode.bean.SearchBean;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/10 14:48
 */
public class SearchBodyHolder<T> extends CustomBasicHolder {

    private float downX, downY, upX, upY, moveGap;

    public SearchBodyHolder(View itemView) {
        super(itemView);
    }

    public SearchBodyHolder(List<T> datas, View itemView) {
        super(datas, itemView);
    }

    public SearchBodyHolder(Context context, List<T> lists, int itemID) {
        super(context, lists, itemID);
    }

    @Override
    public View initView(int position, Context context) {
        TextView textView = (TextView) itemView.findViewById(R.id.tv_mian);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_main);

        final SearchBean.ShowapiResBodyBean.CbListBean data = (SearchBean.ShowapiResBodyBean.CbListBean) datas.get(position);
        Glide.with(context)
                .load((data).imgList.get(0).imgUrl)
//                .override(DeviceUtils.getDeviceWidth(context)[0],DeviceUtils.getDeviceWidth(context)[0]*5/7)
                .placeholder(R.mipmap.defaul)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_launcher)//load失敗的Drawable
                .centerCrop()
                .crossFade()
                .into(imageView);



        textView.setText(data.name);
        final int slop = ViewConfiguration.get(context).getScaledTouchSlop();
        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        excusebigAnimato();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        excusesmlAnimato(data, false);
                        break;
                    case MotionEvent.ACTION_UP:
                        upX = event.getX();
                        upY= event.getY();
                        moveGap = Math.max(Math.abs(upX - downX), Math.abs(upY - downY));
                        if (moveGap > slop) {
                            excusesmlAnimato(data, false);
                        } else {
                            excusesmlAnimato(data, true);
                        }

                        break;
                }


                return true;
            }
        });

        return itemView;
    }

    private void excusebigAnimato() {
        float start = 1.0f;
        if (sml_animator != null && sml_animator.isRunning()) {
            start = (float) sml_animator.getAnimatedValue();
            sml_animator.cancel();
            sml_animator.end();

        }

        big_animator = ValueAnimator.ofFloat(start, 1.08f);
        big_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                itemView.setScaleX(value);
                itemView.setScaleY(value);

            }
        });

        big_animator.setDuration(200);
        big_animator.start();
    }

    private void excusesmlAnimato(final SearchBean.ShowapiResBodyBean.CbListBean data,  boolean isJump) {
        float start = 1.08f;
        if (big_animator != null && big_animator.isRunning()) {
            start = (float) big_animator.getAnimatedValue();
            big_animator.cancel();
            big_animator.end();

        }

        sml_animator = ValueAnimator.ofFloat(start, 1.0f);
        sml_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                itemView.setScaleX(value);
                itemView.setScaleY(value);

            }
        });
        if (isJump){
            sml_animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    onClickHolder(data);

                }
            });
        }


        sml_animator.setDuration(200);
        sml_animator.start();
    }


    private ValueAnimator big_animator, sml_animator;

    public void onClickHolder(SearchBean.ShowapiResBodyBean.CbListBean data) {

    }
}
