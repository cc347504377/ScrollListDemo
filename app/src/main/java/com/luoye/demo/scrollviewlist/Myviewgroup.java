package com.luoye.demo.scrollviewlist;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Luoye on 2016/11/2.
 */

public class Myviewgroup extends LinearLayout implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {
    private LinearLayout title;
    private LinearLayout content;
    private boolean isopen = false;
    private Context context;
    private ValueAnimator valueAnimator;
    private int contentheight;
    private boolean isfirst = true;

    public Myviewgroup(Context context) {
        this(context, null);
    }

    public Myviewgroup(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Myviewgroup(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isfirst) {
            content = (LinearLayout) getChildAt(1);
            title = (LinearLayout) getChildAt(0);
            title.setOnClickListener(this);
            content.setVisibility(VISIBLE);
            contentheight = content.getLayoutParams().height;
            LinearLayout.LayoutParams params = (LayoutParams) content.getLayoutParams();
            params.bottomMargin = -contentheight;
            content.setLayoutParams(params);
            isfirst = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        FrameLayout view = (FrameLayout) getChildAt(0);
//        content = (LinearLayout) view.getChildAt(0);
//        title = (LinearLayout) view.getChildAt(1);


    }

    @Override
    public void onClick(View v) {
        if (isopen) {
            startanimation(0,-contentheight);
        }else {
            startanimation(-contentheight,0);
        }
        isopen = !isopen;
    }

    public synchronized void startanimation(int start, int end) {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(start, end);
            valueAnimator.addUpdateListener(this);
            valueAnimator.setDuration(800);
        }else {
            valueAnimator.setIntValues(start, end);
        }
        valueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        Log.i("TAG", value + "");
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) content.getLayoutParams();
        layoutParams.bottomMargin = value;
        //params 告诉父容器当前的大小
        content.setLayoutParams(layoutParams);
    }
}
