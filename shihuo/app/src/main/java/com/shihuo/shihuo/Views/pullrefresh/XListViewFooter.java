
package com.shihuo.shihuo.Views.pullrefresh;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shihuo.shihuo.R;

public class XListViewFooter extends LinearLayout {
    public final static int STATE_NORMAL = 0;

    public final static int STATE_READY = 1;

    public final static int STATE_LOADING = 2;

    public final static int STATE_NOTDATA = 3;

    public final static int STATE_WAIT = 4;

    public final static int STATE_REFRESH = 5;

    public final static int STATE_HIDE = 6;

    private Context mContext;

    private View mContentView;

    private View mProgressBar;

    private TextView mHintView;

    public XListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setState(int state) {
        mProgressBar.setVisibility (View.INVISIBLE);
        switch (state) {
            default:
                mHintView.setVisibility(View.VISIBLE);
                mHintView.setText(R.string.xlistview_footer_hint_normal);
                break;
            case STATE_READY:
                mHintView.setVisibility(View.VISIBLE);
                mHintView.setText(R.string.xlistview_footer_hint_ready);
                break;
            case STATE_LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mHintView.setText(R.string.xlistview_header_hint_loading);
                break;
            case STATE_NOTDATA:
                mProgressBar.setVisibility(View.INVISIBLE);
                mHintView.setText(R.string.xlistview_footer_hint_notdata);
                break;
            case STATE_WAIT:
                mHintView.setVisibility(View.VISIBLE);
                mHintView.setText(R.string.xlistview_footer_hint_notdata);
                break;
            case STATE_REFRESH:
                mHintView.setVisibility(View.VISIBLE);
                mHintView.setText(R.string.xlistview_footer_hint_refresh);
                break;
            case STATE_HIDE:
                mContentView.setVisibility(View.GONE);
                break;
        }
    }

    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
        return lp.bottomMargin;
    }

    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    public View getContentView() {
        return mContentView;
    }

    /**
     * normal status
     */
    public void normal() {
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility (View.GONE);
    }

    /**
     * loading status
     */
    public void loading() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void initView(Context context) {
        mContext = context;
        RelativeLayout moreView = (RelativeLayout)LayoutInflater.from(mContext).inflate(
                R.layout.xlistview_footer, null);
        addView(moreView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        mContentView = moreView.findViewById(R.id.xlistview_footer_content);
        mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
        mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_hint_textview);
    }

//    public void setHintText(String text)
//    {
//        if(!TextUtils.isEmpty (text))
//        {
//            mHintView.setText (text);
//        }
//    }

}