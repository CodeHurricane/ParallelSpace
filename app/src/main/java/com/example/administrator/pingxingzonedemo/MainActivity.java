package com.example.administrator.pingxingzonedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;

public class MainActivity extends FragmentActivity {

	private ViewPager vp;
	private int[] layouts = {
			R.layout.welcome11,
			R.layout.welcome2,
			R.layout.welcome3
	};
	private WelcompagerTransformer transformer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vp = (ViewPager)findViewById(R.id.vp);
		
		WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
		vp.setOffscreenPageLimit(3);
		vp.setAdapter(adapter);
		
		transformer = new WelcompagerTransformer();
		vp.setPageTransformer(true, transformer);
	
		vp.setOnPageChangeListener(transformer);
	}
	
	class WelcomePagerAdapter extends FragmentPagerAdapter{

		public WelcomePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new TranslateFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("layoutId", layouts[position]);
			bundle.putInt("pageIndex", position);
			f.setArguments(bundle );
			return f;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
		
		
		
	}
	

}
