package com.luoye.demo.scrollviewlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Luoye on 2016/11/2.
 */

public class MyScrollview extends ScrollView {

    private LayoutInflater inflater;
    private boolean isfirst = true;
    public MyScrollview(Context context) {
        this(context,null);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
    }

    private void initview() {
        View view ;
        LinearLayout layout = (LinearLayout) getChildAt(0);
        for (int i = 0; i < 10; i++) {
            view = inflater.inflate(R.layout.viewgroup, null);
            layout.addView(view);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isfirst)
        initview();
    }
}
