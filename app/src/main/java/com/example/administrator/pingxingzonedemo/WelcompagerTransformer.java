package com.example.administrator.pingxingzonedemo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.ViewGroup;

public class WelcompagerTransformer implements PageTransformer, OnPageChangeListener {
	private static final float ROT_MOD = -15f;
	private int pageIndex;
	private boolean pageChanged = true;

	@Override
	public void transformPage(View view, float position) {
		ViewGroup v = (ViewGroup) view.findViewById(R.id.rl);
		final MyScrollView mscv = (MyScrollView) v.findViewById(R.id.mscv);
		View bg1 = v.findViewById(R.id.imageView0);
		View bg2 = v.findViewById(R.id.imageView0_2);
		View bg_container = v.findViewById(R.id.bg_container);
		
		int bg1_green = view.getContext().getResources().getColor(R.color.bg1_green);
		int bg2_blue = view.getContext().getResources().getColor(R.color.bg2_blue);

		Integer tag = (Integer) view.getTag();
		View parent = (View) view.getParent();
		ArgbEvaluator evaluator = new ArgbEvaluator();
		int color = bg1_green;
		if(tag.intValue()==pageIndex){
			switch (pageIndex) {
			case 0:
				color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
				break;
			case 1:
				color = (int) evaluator.evaluate(Math.abs(position), bg2_blue, bg1_green);
				break;
			case 2:
				color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
				break;
			default:
				break;
			}
			parent.setBackgroundColor(color);
		}
		if(position==0){
			System.out.println("position==0");
			if(pageChanged){
				bg1.setVisibility(View.VISIBLE);
				bg2.setVisibility(View.VISIBLE);
				
				ObjectAnimator animator_bg1 = ObjectAnimator.ofFloat(bg1, "translationX", 0,-bg1.getWidth());
				animator_bg1.setDuration(400);
				animator_bg1.addUpdateListener(new AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						mscv.smoothScrollTo((int) (mscv.getWidth()*animation.getAnimatedFraction()), 0);
					}
				});
				animator_bg1.start();
				
				ObjectAnimator animator_bg2 = ObjectAnimator.ofFloat(bg2, "translationX", bg2.getWidth(),0);
				animator_bg2.setDuration(400);
				animator_bg2.start();
				pageChanged= false;
			}
		}else 
		if(position==-1||position==1){//����Ч����ԭ
			bg2.setTranslationX(0);
			bg1.setTranslationX(0);
			mscv.smoothScrollTo(0, 0);
		}else 
			if(position<1&&position>-1){
				
			final float width = bg1.getWidth();
			final float height = bg1.getHeight();
			final float rotation = ROT_MOD * position * -1.25f;

			bg_container.setPivotX(width * 0.5f);
			bg_container.setPivotY(height);
			bg_container.setRotation(rotation);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		System.out.println("position:"+position+", positionOffset:"+positionOffset);
	}

	@Override
	public void onPageSelected(int position) {
		pageIndex = position;
		pageChanged = true;
		System.out.println("position_selected:"+position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

}
