package em.sang.com.ew.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import em.sang.com.allrecycleview.utils.Apputils;
import em.sang.com.ew.R;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

/**
 * Description：
 *
 * @Author桑小年
 * @Data：2016/11/29 10:08
 */
public class SearchView extends LinearLayout {

    private int mWidth, mHeight, currentX, finaX,gap,boom;
    private boolean isShow;
    private Bitmap search;
    private Bitmap cancle;
    private ImageButton imgSearch, imgCancle;
    private EditText et;
    private OnSearchClickListener listener;


    public SearchView(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        search = BitmapFactory.decodeResource(getResources(), R.mipmap.menu_search);
        cancle = BitmapFactory.decodeResource(getResources(), R.mipmap.wrong);
        imgCancle = new ImageButton(getContext());
        imgSearch = new ImageButton(getContext());
        imgCancle.setBackgroundColor(Color.TRANSPARENT);
        imgSearch.setBackgroundColor(Color.TRANSPARENT);
        imgSearch.setImageBitmap(search);
        imgCancle.setImageBitmap(cancle);
        gap = Apputils.dip2px(getContext(),5);

        et = new EditText(getContext());
        et.setMaxLines(1);
        et.setSingleLine(true);
        et.setImeOptions(IME_ACTION_SEARCH);
        et.setLineSpacing(0,0);

        et.setShowSoftInputOnFocus(false);
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);

        et.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN ) {
                    // 先隐藏键盘
                    ((InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(((Activity)getContext())
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_IMPLICIT_ONLY);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    if (listener != null) {
                        String trim = et.getText().toString().trim();

                        listener.onClick(trim);
                    }
                }


                return false;
            }
        });

        imgCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                    ((InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(((Activity)getContext())
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_IMPLICIT_ONLY);
                    et.setShowSoftInputOnFocus(false);
                    et.setFocusable(false);
                    et.setFocusableInTouchMode(false);
                   excuse(false);
                }
            }
        });
        imgSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isShow) {
                    if (listener != null) {
                        String trim = et.getText().toString().trim();
                        listener.onClick(trim);
                    }
                } else {
                    isShow = true;
                    et.setShowSoftInputOnFocus(true);
                    et.setFocusableInTouchMode(true);
                    et.setFocusable(true);
                    excuse(true);
                }
            }
        });

        addView(imgSearch);
        addView(et);
        addView(imgCancle);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


        layoutChild();

    }

    private void layoutChild() {
        getChildAt(0).layout(finaX-gap, 0, finaX + search.getWidth()-gap, mHeight);
        getChildAt(1).layout(currentX +cancle.getWidth()+ gap, gap, currentX + mWidth -search.getWidth(), mHeight+gap);
        getChildAt(2).layout(currentX+gap , 0, currentX+cancle.getWidth()+gap , mHeight);


    }

    ValueAnimator animator, animator1;

    private void excuse(final boolean isShow) {
        int start ;
        int end ;
        if (isShow) {
            end = 0;
            start = mWidth;
        } else {
            start=0;
            end = mWidth;
        }

        if (animator != null && animator.isRunning()) {
            start = (int) animator.getAnimatedValue();
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                currentX = value;
                layoutChild();
                postInvalidate();
            }

        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (isShow){
                    setBackgroundResource(R.drawable.search_bag);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isShow){
                    setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        animator.setDuration(200);
        animator.start();
    }





    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentX = mWidth ;
        finaX = mWidth - search.getWidth();
    }

    public void setOnSearchListener(OnSearchClickListener listenr) {
        this.listener = listenr;
    }

    public interface OnSearchClickListener {
        void onClick(String msg);
    }



}
