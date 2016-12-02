package em.sang.com.allrecycleview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import em.sang.com.allrecycleview.inter.RefrushListener;
import em.sang.com.allrecycleview.utils.Apputils;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/12/1 14:42
 */
public class PullRecycleView extends BasicRecycleView {

    private TextView tv;

    private float downY;
    private RefrushListener listener;
    private int topID = R.layout.heard_refrush;
    private int boomID = R.layout.heard_refrush;
    private LinearLayout topView, boomView;
    private int mearchTop,mearchBoom;
    private int refrush_state = 0;
    /**
     * 正在加载
     */
    private static final int LOADING=0;
    /**
     * 加载成功
     */
    private static final int LOAD_SUCCESS=1;//加载成功

    /**
     * 加载失败
     */
    private static final int LOAD_FAIL=2;//加载失败
    /**
     * 正常状态
     */
    private static final int LOAD_OVER=4;//正常状态
    /**
     * 即将开始加载
     */
    private static final int LOAD_BEFOR=5;//即将开始加载

    /**
     * 滑动距离倍数
     */
    private final int muli = 3;

    public PullRecycleView(Context context) {
        super(context);
        initView(context, null, 0);

    }

    public PullRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);

    }

    public PullRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);

    }

    protected void initView(Context context, @Nullable AttributeSet attrs, int defStyle) {

        downY = -1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        params.gravity = Gravity.CENTER;
        params.height=0;
        topView = (LinearLayout) View.inflate(context, topID, null);
        boomView = (LinearLayout) View.inflate(context, boomID, null);
        mearchTop = Apputils.getWidthAndHeight(topView)[1];
        mearchBoom = Apputils.getWidthAndHeight(boomView)[1];

        tv= (TextView) topView.findViewById(R.id.tv);

        topView.setLayoutParams(params);
        boomView.setLayoutParams(params);

    }

    public View getTopView() {
        return topView;
    }

    public View getBoomView() {
        return boomView;
    }


    public void setTopView(View view) {

        topView.addView(view);
        mearchTop = Apputils.getWidthAndHeight(topView)[1];
    }
    public void setBoomView(View view) {

        boomView.addView(view);
        mearchBoom = Apputils.getWidthAndHeight(boomView)[1];
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (downY == -1) {
            downY = e.getRawY();
        }

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float gap = (e.getRawY() - downY)/muli;
                downY = e.getRawY();
                if (isShowUp() || getHeightVisiable(topView)>0) {
                    setHightVisiable(topView, gap);
                }
                break;
            default:
                downY = -1;
                if (isFirst()) {
                   moveToChildHight(topView);
                }
                break;
        }


        return super.onTouchEvent(e);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (getHeightVisiable(topView)>0&&state==RecyclerView.SCROLL_STATE_SETTLING) {
            setHightVisiable(topView, -dy);
        }
    }
    /**
     * 获取当前控件高度
     *
     * @param view
     * @return
     */
    private int getHeightVisiable(LinearLayout view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams.height;
    }

    /**
     * 设置当前控件高度
     *
     * @param view
     * @param gap
     */
    private void setHightVisiable(LinearLayout view, float gap) {
        if (animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        int height = (int) (getHeightVisiable(view) + gap);
        height=height>=0?height:0;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        if (getHeightVisiable(topView)!=0){
            scrollToPosition(0);
        }
        //根据高度更新状态
        upRefrush_state(getRefrush_state(height ));
    }

    private int getRefrush_state(int height ){
        int result = LOAD_OVER;

        int stand = 0;
        if (isFirst()){
            stand = mearchTop;
        }else if (isLast()){
            stand=mearchBoom;
        }
        if (height<stand/2){
            result=LOAD_OVER;
        }else {
            result=LOAD_BEFOR;
        }
        return result;
    }
    private void upRefrush_state(int refrush_state){
        switch (refrush_state){
            case LOAD_OVER:
                tv.setText("下拉刷新数据");
                break;
            case LOAD_BEFOR:
               tv.setText("松手刷新数据");
                break;
            case LOADING:
                tv.setText("正在加载数据");
                break;
            case LOAD_FAIL:
                tv.setText("加载失败");
                break;
            case LOAD_SUCCESS:
                tv.setText("加载成功!");
                break;
            default:
                tv.setText("加载异常");
                break;
        }

    }




    /**
     * 是否正在下拉
     *
     * @return
     */
    private boolean isShowUp() {

        return topView.getChildCount() > 0 && isFirst() && state == RecyclerView.SCROLL_STATE_DRAGGING;
    }


    private ValueAnimator animator;

    private void moveToChildHight(final LinearLayout view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int height = layoutParams.height;
        int stand = 0;
        if (isFirst()){
            stand = mearchTop;
        }else if (isLast()){
            stand=mearchBoom;
        }

        if (height<stand/2){
            stand=0;
        }
        animator = ValueAnimator.ofFloat(height, stand);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) value;
                view.setLayoutParams(layoutParams);
            }
        });

        animator.setDuration(200);
        animator.start();
    }


}
