package com.gys.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyIndicatorView extends FrameLayout implements OnPageChangeListener{

	private Context context;
	private ViewPager mViewPager;
	private int count;
	private LinearLayout ll_title;
	private LinearLayout ll_indicator;
	private View v_indicator;

	public MyIndicatorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public MyIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public MyIndicatorView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		View.inflate(context,R.layout.indicator_bg,this);
		ll_title = (LinearLayout) findViewById(R.id.ll_title);
		ll_indicator = (LinearLayout) findViewById(R.id.ll_indicator);
		v_indicator = findViewById(R.id.v_indicator);
	}
	
	public void setViewPager(ViewPager mViewPager){
		this.mViewPager = mViewPager;
		PagerAdapter adapter = mViewPager.getAdapter();
		if(adapter == null)throw new RuntimeException("You must setViewPager After ViewPager.setAdapter()!");
		this.count = adapter.getCount();
		ll_indicator.setWeightSum(count);
		for (int i = 0; i < count; i++) {
			TextView tv = new TextView(context);
			tv.setText(adapter.getPageTitle(i));
			tv.setGravity(Gravity.CENTER);
			ll_title.addView(tv);
			tv.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
		}
		mViewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float percent, int arg2) {
		if(mViewPager != null){
			int width = ll_indicator.getWidth()/mViewPager.getAdapter().getCount();
			int left = (int) ((arg0+1*percent)*width);
			v_indicator.layout(left,v_indicator.getTop(),left+width,v_indicator.getBottom());
		}
	}

	@Override
	public void onPageSelected(int arg0) {
	}
	
	

}
