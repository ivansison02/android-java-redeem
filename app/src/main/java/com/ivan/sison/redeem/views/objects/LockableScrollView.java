package com.ivan.sison.redeem.views.objects;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LockableScrollView extends ScrollView {

    private boolean mCanScroll = false;

    public LockableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean canScroll) {
        this.mCanScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mCanScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mCanScroll && super.onInterceptTouchEvent(ev);
    }
}

