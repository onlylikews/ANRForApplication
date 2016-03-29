package com.bingo.anrforapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by tanzhh on 2016/3/28.
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setBackgroundResource(R.drawable._welcome_bg);
		setContentView(textView);
		textView.setGravity(Gravity.CENTER);
		textView.setText("我是主界面");
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
	}
}
